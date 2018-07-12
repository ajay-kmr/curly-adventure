package com.example.multidbjpa.dao.db2.repoService;

import com.example.multidbjpa.dao.db2.entity.Customer;
import com.example.multidbjpa.dao.db2.repository.CustomerRepository;
import com.example.multidbjpa.dao.shared.repoService.BaseRepoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class CustomerRepoService extends BaseRepoService<Customer, Long> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected JpaRepository<Customer, Long> getRepository() {
        return customerRepository;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
