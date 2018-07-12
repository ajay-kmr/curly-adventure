package com.example.multidbjpa.dao.db2.config;


import com.example.multidbjpa.dao.db2.repository.Db2UserRepository;
import com.example.multidbjpa.dao.shared.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
public class Db2AuditorAwareImpl implements AuditorAware<User> {

    private Db2UserRepository userRepository;

    Db2AuditorAwareImpl(Db2UserRepository userRepository) {
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
