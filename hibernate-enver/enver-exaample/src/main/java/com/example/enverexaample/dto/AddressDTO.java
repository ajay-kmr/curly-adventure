package com.example.enverexaample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO extends BaseEntityDTO {

    private String city;

    private String province;

    private String state;

    private String country;
}
