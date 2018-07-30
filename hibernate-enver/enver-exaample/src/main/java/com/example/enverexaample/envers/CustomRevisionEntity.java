package com.example.enverexaample.envers;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@RevisionEntity(CustomRevisionEntityListener.class)
public class CustomRevisionEntity extends DefaultRevisionEntity {

    private String username;
    private String ipAddress;

    private String additionalRevisionInfo;
}
