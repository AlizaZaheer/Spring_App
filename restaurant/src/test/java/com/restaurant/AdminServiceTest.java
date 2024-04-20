package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.restaurant.pojo.Admin;
import com.restaurant.repo.AdminRepo;
import com.restaurant.service.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepo adminRepo;
    
    @Test
    public void testRegisterAdmin() {
        Admin admin = new Admin();
        admin.setEmail("test@example.com");
        admin.setEmployeeId("EMP123");
        admin.setName("Admin New");
        admin.setPassword("@Bc1234");
        adminService.registerAdmin(admin);
        
        verify(adminRepo, times(1)).save(admin);
    }

    @Test
    public void testCheckAdminCredentials_Valid() {
        Admin admin = new Admin();
        admin.setEmail("akshay@gmail.com");
        admin.setPassword("@Bc12342323");
        
        when(adminRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(admin);
        
        String result = adminService.checkAdminCredentials(admin.getEmail(), admin.getPassword());
        
        assertEquals(admin.getEmployeeId(), result);
    }

    @Test
    public void testCheckAdminCredentials_Invalid() {
        when(adminRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);
        
        String result = adminService.checkAdminCredentials("invalid@example.com", "invalidpassword");
        
        assertEquals("invalid", result);
    }

    @Test
    public void testUpdateAdmin() {
        Admin admin = new Admin();
        admin.setEmail("akshay@gmail.com");
        admin.setEmployeeId("@Bc12345");
        
        when(adminRepo.save(any(Admin.class))).thenReturn(admin);
        
        Admin updatedAdmin = adminService.updateAdmin(admin);
        
        assertNotNull(updatedAdmin);
        assertEquals(admin, updatedAdmin);
    }

}

