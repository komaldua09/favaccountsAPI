package com.learning.bank.service;

import com.learning.bank.enums.ErrorCodes;
import com.learning.bank.exception.BusinessException;
import com.learning.bank.model.Customer;
import com.learning.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    public Customer fetchCustomerById(long customerId) {
        Optional<Customer> foundCustomer = customerRepository.findByCustomerId(customerId);
        if(foundCustomer.isPresent()) {
            return foundCustomer.get();
        } else{
            throw BusinessException.businessException(ErrorCodes.CUSTOMER_NOT_FOUND);
        }


    }
}
