package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.pojo.ContactForm;
import com.restaurant.service.ContactFormService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contact-form")
public class ContactFormController {
	@Autowired
	ContactFormService contactFormService;
	
	@PostMapping("")
	public String submitForm(@RequestBody ContactForm contactForm) {
		if(contactFormService.submitForm(contactForm)!=null)
			return "Your Query has been submitted successfully! We'll get back to you soon";
		return "Oops, something went wrong! Please Try again!";
	}
	@GetMapping("")
	public List<ContactForm> getAllForms(){
		return contactFormService.getAllForms();
		
	}
	@GetMapping("/{contactFormId}")
    public ContactForm getFormById(@PathVariable Long contactFormId) {
        return contactFormService.getContactFormById(contactFormId);
    }
	
	@DeleteMapping("/{contactFormId}")
	public String queryCleared(@PathVariable Long contactFormId) {
		contactFormService.deleteForm(contactFormId);
		return "done";
	}
}