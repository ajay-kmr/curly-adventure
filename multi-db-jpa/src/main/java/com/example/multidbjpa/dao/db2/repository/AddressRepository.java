package com.example.multidbjpa.dao.db2.repository;

import com.example.multidbjpa.dao.db2.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
