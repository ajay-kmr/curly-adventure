package com.example.multidbjpa.dao.db2.repoService;

import com.example.multidbjpa.dao.db2.entity.Address;
import com.example.multidbjpa.dao.db2.repository.AddressRepository;
import com.example.multidbjpa.dao.shared.BaseRepoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Service
@CommonsLog
public class AddressRepoService extends BaseRepoService<Address, Long> {


    @Autowired
    AddressRepository addressRepository;

    @Override
    protected JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

}
