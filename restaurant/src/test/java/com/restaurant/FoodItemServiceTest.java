package com.restaurant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.pojo.CartItem;
import com.restaurant.pojo.Category;
import com.restaurant.pojo.FoodItem;
import com.restaurant.repo.CartRepo;
import com.restaurant.repo.CategoryRepo;
import com.restaurant.repo.FoodItemRepo;
import com.restaurant.service.FoodItemService;

@ExtendWith(MockitoExtension.class)
public class FoodItemServiceTest {

    @Mock
    private FoodItemRepo foodItemRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private CartRepo cartRepo;

    @InjectMocks
    private FoodItemService foodItemService;

    @Test
    public void testGetValidFoodItems() {
        List<FoodItem> expectedFoodItems = new ArrayList<>();
        when(foodItemRepo.findValidFoodItems()).thenReturn(expectedFoodItems);

        List<FoodItem> result = foodItemService.getValidFoodItems();

        assertEquals(expectedFoodItems, result);
        verify(foodItemRepo, times(1)).findValidFoodItems();
    }

    @Test
    public void testGetFoodItems() {
        List<FoodItem> expectedFoodItems = new ArrayList<>();
        when(foodItemRepo.findAll()).thenReturn(expectedFoodItems);

        List<FoodItem> result = foodItemService.getFoodItems();

        assertEquals(expectedFoodItems, result);
        verify(foodItemRepo, times(1)).findAll();
    }

    @Test
    public void testGetFoodItemsByCategoryId() {
        Long categoryId = 1L;
        Category category = new Category();
        List<FoodItem> expectedFoodItems = new ArrayList<>();
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(foodItemRepo.findByCategory(category)).thenReturn(expectedFoodItems);

        List<FoodItem> result = foodItemService.getFoodItemsByCategoryId(categoryId);

        assertEquals(expectedFoodItems, result);
        verify(categoryRepo, times(1)).findById(categoryId);
        verify(foodItemRepo, times(1)).findByCategory(category);
    }

    @Test
    public void testGetFoodByName() {
        String foodName = "Test Food";
        FoodItem expectedFoodItem = new FoodItem();
        when(foodItemRepo.findByName(foodName)).thenReturn(expectedFoodItem);

        FoodItem result = foodItemService.getFoodByName(foodName);

        assertEquals(expectedFoodItem, result);
        verify(foodItemRepo, times(1)).findByName(foodName);
    }

    @Test
    public void testAddFoodItem() {
        FoodItem foodItemToAdd = new FoodItem();
        when(foodItemRepo.save(foodItemToAdd)).thenReturn(foodItemToAdd);

        FoodItem result = foodItemService.addFoodItem(foodItemToAdd);

        assertEquals(foodItemToAdd, result);
        verify(foodItemRepo, times(1)).save(foodItemToAdd);
    }

    @Test
    public void testEditFoodItem() {
        FoodItem foodItemToEdit = new FoodItem();
        when(foodItemRepo.save(foodItemToEdit)).thenReturn(foodItemToEdit);

        FoodItem result = foodItemService.editFoodItem(foodItemToEdit);

        assertEquals(foodItemToEdit, result);
        verify(foodItemRepo, times(1)).save(foodItemToEdit);
       
    }

    @Test
    public void testDeleteFoodItemById() {
        Long foodItemIdToDelete = 1L;
        FoodItem foodItemToDelete = new FoodItem();
        when(foodItemRepo.findById(foodItemIdToDelete)).thenReturn(Optional.of(foodItemToDelete));
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        when(cartRepo.findByFoodItem(foodItemToDelete)).thenReturn(cartItems);

        foodItemService.deleteFoodItemById(foodItemIdToDelete);

        verify(foodItemRepo, times(1)).findById(foodItemIdToDelete);
        verify(cartRepo, times(1)).findByFoodItem(foodItemToDelete);
        verify(cartRepo, times(1)).delete(any(CartItem.class));
        verify(foodItemRepo, times(1)).deleteById(foodItemIdToDelete);
    }

}
