package com.example.enverexaample.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;
}
