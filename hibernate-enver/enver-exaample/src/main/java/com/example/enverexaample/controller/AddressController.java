package com.example.enverexaample.controller;

import com.example.enverexaample.dto.AddressDTO;
import com.example.enverexaample.dto.ResponseDTO;
import com.example.enverexaample.services.AddressService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/address")
@CommonsLog
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("save")
    ResponseDTO<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.save(addressDTO);
    }
}
