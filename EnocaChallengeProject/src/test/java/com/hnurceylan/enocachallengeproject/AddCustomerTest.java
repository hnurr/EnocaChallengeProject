package com.hnurceylan.enocachallengeproject;


import com.hnurceylan.enocachallengeproject.entity.Customer;
import com.hnurceylan.enocachallengeproject.repository.CustomerRepository;
import com.hnurceylan.enocachallengeproject.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCustomerTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
    }

    @Test
    void testAddCustomerNameNull() {
        customer.setName(null);
        customer.setMail("test@mail.com");

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("Name cannot be null or empty", response.getBody());
    }

    @Test
    void testAddCustomerNameEmpty() {
        customer.setName(" ");
        customer.setMail("test@mail.com");

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("Name cannot be null or empty", response.getBody());
    }

    @Test
    void testAddCustomerEmailNull() {
        customer.setName("Test User");
        customer.setMail(null);

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("Email cannot be null or empty", response.getBody());
    }

    @Test
    void testAddCustomerEmailEmpty() {
        customer.setName("Test User");
        customer.setMail(" ");

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("Email cannot be null or empty", response.getBody());
    }

    @Test
    void testAddCustomerEmailAlreadyExists() {
        customer.setName("Test User");
        customer.setMail("test@mail.com");

        when(customerRepository.existsByMail(customer.getMail())).thenReturn(true);

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("Email already exists", response.getBody());
    }

    @Test
    void testAddCustomerSuccess() {
        customer.setName("Test User");
        customer.setMail("test@mail.com");


        when(customerRepository.existsByMail(customer.getMail())).thenReturn(false);

        ResponseEntity<?> response = customerService.addCustomer(customer);

        assertEquals("customer successfully registered", response.getBody());
        verify(customerRepository).save(customer);
    }



}
