package com.example.enverexaample.dao.repository;

import com.example.enverexaample.dao.entity.Address;
import com.example.enverexaample.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
