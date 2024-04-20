package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.pojo.User;
import com.restaurant.repo.UserRepo;
import com.restaurant.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setMobileNumber("1234567890");
        user.setName("User New");
        user.setPassword("@Bc1234");
        userService.registerUser(user);

        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testCheckUserCredentials_Valid() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("@Bc1234");

        when(userRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);

        String result = userService.checkUserCredentials(user.getEmail(), user.getPassword());

        assertEquals(user.getEmail(), result);
    }

    @Test
    public void testCheckUserCredentials_Invalid() {
        when(userRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        String result = userService.checkUserCredentials("invalid@example.com", "invalidpassword");

        assertEquals("invalid", result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setMobileNumber("1234567890");

        when(userRepo.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertEquals(user, updatedUser);
    }
}
