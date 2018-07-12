package com.example.multidbjpa.dao.db1.repoService;

import com.example.multidbjpa.dao.db1.repository.Db1UserRepository;
import com.example.multidbjpa.dao.shared.User;
import com.example.multidbjpa.dao.shared.UserRepoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
@CommonsLog
public class Db1UserRepoService extends UserRepoService {

    @Autowired
    Db1UserRepository db1UserRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return db1UserRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

}
