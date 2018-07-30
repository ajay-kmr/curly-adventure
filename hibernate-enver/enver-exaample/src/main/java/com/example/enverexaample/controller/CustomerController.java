package com.example.enverexaample.controller;

import com.example.enverexaample.dto.CustomerDTO;
import com.example.enverexaample.dto.ResponseDTO;
import com.example.enverexaample.services.CustomerService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
@CommonsLog
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("save")
    ResponseDTO<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }
}
