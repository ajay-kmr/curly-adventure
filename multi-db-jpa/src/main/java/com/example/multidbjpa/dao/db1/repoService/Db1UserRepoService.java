package com.example.multidbjpa.dao.db1.repoService;

import com.example.multidbjpa.dao.db1.repository.Db1UserRepository;
import com.example.multidbjpa.dao.shared.entity.User;
import com.example.multidbjpa.dao.shared.repoService.UserRepoService;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class Db1UserRepoService extends UserRepoService {

    @Autowired
    Db1UserRepository db1UserRepository;

    @Override
    protected BaseRepository<User, Long> getRepository() {
        return db1UserRepository;
    }

}
