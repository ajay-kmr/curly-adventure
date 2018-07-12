package com.example.multidbjpa.dao.db2.repository;


import com.example.multidbjpa.dao.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2UserRepository extends JpaRepository<User, Long> {

}