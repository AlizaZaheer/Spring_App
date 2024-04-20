package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.restaurant.pojo.Category;
import com.restaurant.repo.CategoryRepo;
import com.restaurant.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testGetCategories() {
        List<Category> expectedCategories = new ArrayList<>();
        when(categoryRepo.findAll()).thenReturn(expectedCategories);

        List<Category> result = categoryService.getCategories();

        assertEquals(expectedCategories, result);
        verify(categoryRepo, times(1)).findAll();
    }

    @Test
    public void testGetCategoryByName() {
        String categoryName = "Test Category";
        Category expectedCategory = new Category();
        when(categoryRepo.findByName(categoryName)).thenReturn(expectedCategory);

        Category result = categoryService.getCategoryByName(categoryName);

        assertEquals(expectedCategory, result);
        verify(categoryRepo, times(1)).findByName(categoryName);
    }

    @Test
    public void testAddCategory() {
        Category categoryToAdd = new Category();

        categoryService.addCategory(categoryToAdd);

        verify(categoryRepo, times(1)).save(categoryToAdd);
    }

    @Test
    public void testDeleteCategoryById() {
        Long categoryIdToDelete = 1L;

        categoryService.deleteCategoryById(categoryIdToDelete);

        verify(categoryRepo, times(1)).deleteById(categoryIdToDelete);
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Category result = categoryService.getCategoryById(categoryId);

        assertEquals(expectedCategory, result);
        verify(categoryRepo, times(1)).findById(categoryId);
    }
}
