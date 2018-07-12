package com.example.multidbjpa.dao.db1.config;


import com.example.multidbjpa.dao.db1.repository.Db1UserRepository;
import com.example.multidbjpa.dao.shared.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
public class Db1AuditorAwareImpl implements AuditorAware<User> {

    private Db1UserRepository userRepository;

    Db1AuditorAwareImpl(Db1UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@Override
    public User getCurrentAuditor() {
        //TODO
//        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return null;
    }*/

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.empty();
    }
}
