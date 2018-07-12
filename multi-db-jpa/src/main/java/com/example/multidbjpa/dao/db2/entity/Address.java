package com.example.multidbjpa.dao.db2.entity;

import com.example.multidbjpa.dao.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Address extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    private String addressLine1;
    private String addressLine2;
    private String pinCode;
    private String city;
    private String state;
    private String country;

}
