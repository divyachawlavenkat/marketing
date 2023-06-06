package com.allica_test.marketing.controller;

import com.allica_test.marketing.model.Customer;
import com.allica_test.marketing.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerImpl;

    @PostMapping("/customer/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {

        try {
            Customer customerObj = customerImpl.saveCustomer(customer);
            return new ResponseEntity<>(customerObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {

            List<Customer> customers = new ArrayList<>(customerImpl.getAllCustomers());

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable(value = "id") Integer id) {
        Optional<Customer> customer = customerImpl.getCustomerById(id);
      return customer.map(_customer->new ResponseEntity<>(customer,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer customer) {
        Optional<Customer> customerData = customerImpl.getCustomerById(id);
        if (customerData.isPresent()) {
            Customer _customer = customerData.get();
            _customer.setFirst_name(customer.getFirst_name());
            _customer.setLast_name(customer.getLast_name());
            _customer.setMarket_pre(customer.getMarket_pre());
            return new ResponseEntity<>(customerImpl.saveCustomer(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable(value = "id") Integer id) {
        try {
            customerImpl.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}




