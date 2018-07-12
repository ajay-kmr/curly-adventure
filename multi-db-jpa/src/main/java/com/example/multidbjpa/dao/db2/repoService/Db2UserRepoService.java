package com.example.multidbjpa.dao.db2.repoService;

import com.example.multidbjpa.dao.db2.repository.Db2UserRepository;
import com.example.multidbjpa.dao.shared.entity.User;
import com.example.multidbjpa.dao.shared.repoService.UserRepoService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class Db2UserRepoService extends UserRepoService {

    @Autowired
    Db2UserRepository db2UserRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return db2UserRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
