package com.example.multidbjpa.dao.db2.repository;

import com.example.multidbjpa.dao.db2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
