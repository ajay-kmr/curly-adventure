package com.example.multidbjpa.dao.db2.repository;

import com.example.multidbjpa.dao.db2.entity.Customer;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
}
