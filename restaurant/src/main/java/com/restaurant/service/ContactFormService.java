package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.pojo.ContactForm;
import com.restaurant.repo.ContactFormRepo;

@Service
public class ContactFormService {

	@Autowired
	ContactFormRepo contactFormRepo;

	public ContactForm submitForm(ContactForm contactForm) {
		return contactFormRepo.save(contactForm);
	}
	public List<ContactForm> getAllForms(){
		return contactFormRepo.findAll();
	}
	public void deleteForm(Long contactFormId) {
		contactFormRepo.deleteById(contactFormId);
	}
	public ContactForm getContactFormById(Long contactFormId) {
        return contactFormRepo.findById(contactFormId).orElse(null);
    }
}