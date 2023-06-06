package com.allica_test.marketing.service;

import com.allica_test.marketing.model.Customer;
import com.allica_test.marketing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;

   @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
       this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }


}



