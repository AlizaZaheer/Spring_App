package com.restaurant;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	AdminServiceTest.class, 
	CartServiceTest.class, 
	CategoryServiceTest.class, 
	ContactFormServiceTest.class,		
	FoodItemServiceTest.class, 
	OrderServiceTest.class, 
	ReviewServiceTest.class, 
	UserServiceTest.class })
public class AllTests {

}
