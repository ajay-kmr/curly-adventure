package com.example.multidbjpa.dao.db2.repository;


import com.example.multidbjpa.dao.shared.entity.User;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2UserRepository extends BaseRepository<User, Long> {

}