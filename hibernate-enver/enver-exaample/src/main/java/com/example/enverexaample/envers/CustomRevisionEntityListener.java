package com.example.enverexaample.envers;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionEntityListener implements RevisionListener {
    public final static String USERNAME = "Ajay";
    public final static String ADDITIONAL_INFO = "Additional information";
    public static Long ipAddress = 100l;

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setUsername(USERNAME);
        customRevisionEntity.setIpAddress((ipAddress++).toString());
        customRevisionEntity.setAdditionalRevisionInfo("Additional Information");
    }
}