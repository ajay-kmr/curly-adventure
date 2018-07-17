package com.example.multidbjpa.controller;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.db1.repository.CatalogRepository;
import com.example.multidbjpa.dao.db2.entity.Address;
import com.example.multidbjpa.dao.db2.repository.AddressRepository;
import com.example.multidbjpa.dao.shared.repository.BaseRepositoryImpl;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;

@RestController
@RequestMapping("api/v1")
public class TestController {

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    @Qualifier("db1EntityManagerFactory")
    EntityManagerFactory db1EntityManagerFactory;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    @Qualifier("db2EntityManagerFactory")
    EntityManagerFactory db2EntityManagerFactory;

    @RequestMapping("test")
    ResponseEntity testMethod() throws Exception {

        Assert.isTrue((
                        (Advised) addressRepository).getTargetSource().getTarget().getClass() == BaseRepositoryImpl.class,
                "Assertion failed");
        Assert.isTrue((
                        (Advised) catalogRepository).getTargetSource().getTarget().getClass() == BaseRepositoryImpl.class,
                "Assertion failed");

        Assert.isAssignable(addressRepository.getEntityClass(), Address.class);
        Assert.isAssignable(catalogRepository.getEntityClass(), Catalog.class);

        Assert.isTrue(addressRepository.getEntityManager().getEntityManagerFactory() == db2EntityManagerFactory, " Assertion failed");
        Assert.isTrue(catalogRepository.getEntityManager().getEntityManagerFactory() == db1EntityManagerFactory, " Assertion failed");

        return new ResponseEntity("Everything is Ok", HttpStatus.OK);
    }
}
