package com.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.pojo.ContactForm;

public interface ContactFormRepo extends JpaRepository<ContactForm, Long> {

}
