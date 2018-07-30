package com.example.enverexaample.services;

import com.example.enverexaample.dao.entity.Address;
import com.example.enverexaample.dao.repository.AddressRepository;
import com.example.enverexaample.dto.AddressDTO;
import com.example.enverexaample.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ResponseDTO<AddressDTO> save(AddressDTO addressDTO) {
        ResponseDTO<AddressDTO> responseDTO = new ResponseDTO<>(Boolean.FALSE, "unable to save");
        Address address = null;
        if (addressDTO.getId() != null) {
            Optional<Address> addressOptional = addressRepository.findById(addressDTO.getId());
            if (addressOptional.isPresent()) {
                address = addressOptional.get();
            }
        } else {
            address = new Address();
        }
        if (address != null) {
            address.setCity(addressDTO.getCity());
            address.setProvince(addressDTO.getProvince());
            address.setState(addressDTO.getState());
            address.setCountry(addressDTO.getCountry());
            address = addressRepository.save(address);
            addressDTO.setId(address.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage("Request processed successfully");
        }
        responseDTO.setData(addressDTO);
        return responseDTO;
    }
}
