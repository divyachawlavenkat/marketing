package com.allica_test.marketing.service;

import com.allica_test.marketing.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);

}
