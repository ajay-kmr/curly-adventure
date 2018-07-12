package com.example.multidbjpa.dao.db1.repository;


import com.example.multidbjpa.dao.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db1UserRepository extends JpaRepository<User, Long> {

}