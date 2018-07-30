package com.example.enverexaample.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Address extends BaseEntity {

    /**
     * Audit selected filed instead of all field of Address Entity
     */

    @Audited
    private String city;

    @Audited
    private String province;

    private String state;

    private String country;
}
