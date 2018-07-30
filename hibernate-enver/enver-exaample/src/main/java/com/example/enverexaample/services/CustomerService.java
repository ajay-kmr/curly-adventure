package com.example.enverexaample.services;

import com.example.enverexaample.dao.entity.Customer;
import com.example.enverexaample.dao.repository.CustomerRepository;
import com.example.enverexaample.dto.CustomerDTO;
import com.example.enverexaample.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ResponseDTO<CustomerDTO> save(CustomerDTO customerDTO) {
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<>(Boolean.FALSE, "unable to save");
        Customer customer = null;
        if (customerDTO.getId() != null) {
            Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
            if (customerOptional.isPresent()) {
                customer = customerOptional.get();
            }
        } else {
            customer = new Customer();
        }
        if (customer != null) {
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer = customerRepository.save(customer);
            customerDTO.setId(customer.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage("Request processed successfully");
        }
        responseDTO.setData(customerDTO);
        return responseDTO;
    }
}
