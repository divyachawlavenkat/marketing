package com.allica_test.marketing.unitTest;

import com.allica_test.marketing.model.Customer;
import com.allica_test.marketing.model.MarketingPre;
import com.allica_test.marketing.repository.CustomerRepository;
import com.allica_test.marketing.service.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceUnitTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void whenGetAllUsers_thenReturnListOfUsers() {
        Customer customer = new Customer();
        customer.setFirst_name("Divya");
        customer.setLast_name("Venkatesh");
        customer.setMarket_pre(MarketingPre.EMAIL);

        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertThat(customers).hasSize(1);
        assertThat(customers.get(0)).isEqualTo(customer);

        verify(customerRepository, times(1)).findAll();
    }
    
}
