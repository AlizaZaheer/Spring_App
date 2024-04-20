package com.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.pojo.ContactForm;
import com.restaurant.repo.ContactFormRepo;
import com.restaurant.service.ContactFormService;

@ExtendWith(MockitoExtension.class)
public class ContactFormServiceTest {

    @InjectMocks
    private ContactFormService contactFormService;

    @Mock
    private ContactFormRepo contactFormRepo;

    @Test
    public void testSubmitForm() {
        ContactForm contactForm = new ContactForm();
        contactForm.setName("John");
        contactForm.setEmail("john@example.com");
        contactForm.setSubject("Inquiry");
        contactForm.setMessage("Hello, I have a question.");

        when(contactFormRepo.save(contactForm)).thenReturn(contactForm);

        ContactForm submittedForm = contactFormService.submitForm(contactForm);

        assertEquals(contactForm, submittedForm);
        verify(contactFormRepo).save(contactForm);
    }

    @Test
    public void testGetAllForms() {
        List<ContactForm> forms = new ArrayList<>();
        ContactForm form1 = new ContactForm();
        form1.setName("Alice");
        ContactForm form2 = new ContactForm();
        form2.setName("Bob");
        forms.add(form1);
        forms.add(form2);

        when(contactFormRepo.findAll()).thenReturn(forms);

        List<ContactForm> retrievedForms = contactFormService.getAllForms();

        assertEquals(forms, retrievedForms);
    }

    @Test
    public void testDeleteForm() {
        Long formId = 1L;

        contactFormService.deleteForm(formId);

        verify(contactFormRepo).deleteById(formId);
    }
}
