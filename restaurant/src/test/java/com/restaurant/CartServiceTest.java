package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.exceptions.ItemNotFoundException;
import com.restaurant.pojo.CartItem;
import com.restaurant.pojo.Category;
import com.restaurant.pojo.FoodItem;
import com.restaurant.pojo.User;
import com.restaurant.repo.CartRepo;
import com.restaurant.repo.FoodItemRepo;
import com.restaurant.repo.UserRepo;
import com.restaurant.service.CartService;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepo cartRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private FoodItemRepo foodItemRepo;

    private User testUser;
    private Category testCategory;
    private FoodItem testFoodItem;
    private CartItem testCartItem;

    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setUserId(1L);
        
        testCategory=new Category();
        testCategory.setName("Test Category");
        
        testFoodItem = new FoodItem();
        testFoodItem.setFoodItemId(1L);
        testFoodItem.setName("Test Food Item");
        testFoodItem.setDescription("Test Description");
        testFoodItem.setActualPrice(250.0);
        testFoodItem.setAvailableQuantity(50);
        testFoodItem.setOffer(15);
        testFoodItem.setCategory(testCategory);

        testCartItem = new CartItem();
        testCartItem.setCartItemId(1L);
        testCartItem.setUserId(testUser.getUserId());
        testCartItem.setFoodItem(testFoodItem);
        testCartItem.setQuantity(2);
        testCartItem.setTotalFoodItemCost(testCartItem.getFoodItem().getDiscountedPrice() * testCartItem.getQuantity());
    }

    @Test
    public void testAddItemToCart_Success() {
        when(userRepo.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(foodItemRepo.findById(anyLong())).thenReturn(Optional.of(testFoodItem));
        when(cartRepo.findByUserIdAndFoodItem(anyLong(), any(FoodItem.class))).thenReturn(null);
        when(cartRepo.save(any(CartItem.class))).thenReturn(testCartItem);

        cartService.addItemToCart(testUser.getUserId(), testFoodItem.getFoodItemId(), testCartItem);

        verify(cartRepo, times(1)).save(any(CartItem.class));
    }

    @Test
    public void testAddItemToCart_ItemNotFound() {

        try {
            cartService.addItemToCart(testUser.getUserId(), testFoodItem.getFoodItemId(), testCartItem);
        } catch (ItemNotFoundException e) {
        	System.out.println("Item Not Found - Test to check Item Not Found: "+ e);
        }

        verify(cartRepo, times(0)).save(any(CartItem.class));
    }

    @Test
    public void testUpdateCartItem_Success() {
        when(cartRepo.findById(anyLong())).thenReturn(Optional.of(testCartItem));
        when(cartRepo.save(any(CartItem.class))).thenReturn(testCartItem);

        CartItem updatedCartItem = cartService.updateCartItem(testCartItem.getCartItemId(), 3);

        verify(cartRepo, times(1)).save(any(CartItem.class));
        assertEquals(3, updatedCartItem.getQuantity());
    }

    @Test
    public void testUpdateCartItem_DeleteItem() {
        when(cartRepo.findById(anyLong())).thenReturn(Optional.of(testCartItem));

        CartItem updatedCartItem = cartService.updateCartItem(testCartItem.getCartItemId(), 0);

        verify(cartRepo, times(1)).deleteById(anyLong());
        assertNull(updatedCartItem);
    }

    @Test
    public void testGetWholeCartByUserId() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(testCartItem);

        when(cartRepo.findAllByUserId(anyLong())).thenReturn(cartItems);

        List<CartItem> result = cartService.getWholeCartByUserId(testUser.getUserId());

        assertEquals(1, result.size());
    }

    @Test
    public void testGetCartItemById() {
        when(cartRepo.findById(anyLong())).thenReturn(Optional.of(testCartItem));

        CartItem result = cartService.getCartItemById(testCartItem.getCartItemId());

        assertNotNull(result);
        assertEquals(testCartItem.getCartItemId(), result.getCartItemId());
    }

    @Test
    public void testDeleteFromCart() {
        cartService.deleteFromCart(testCartItem.getCartItemId());

        verify(cartRepo, times(1)).deleteById(anyLong());
    }

    @Test
    public void testDeleteFromCartByFoodItems() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(testCartItem);

        when(cartRepo.findByFoodItem(any(FoodItem.class))).thenReturn(cartItems);

        
        
        doNothing().when(cartRepo).delete(any(CartItem.class));

        cartService.deleteFromCartByFoodItems(Collections.singletonList(testFoodItem));

        verify(cartRepo, times(1)).delete(any(CartItem.class));
    }

    @Test
    public void testDeleteFromCartByUserId() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(testCartItem);

        when(cartRepo.findByUserId(anyLong())).thenReturn(cartItems);

        cartService.deleteFromCartByUserId(testUser.getUserId());

        verify(cartRepo, times(1)).delete(any(CartItem.class));
    }

}
