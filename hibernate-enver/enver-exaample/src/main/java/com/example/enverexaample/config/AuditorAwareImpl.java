package com.example.enverexaample.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Creating implementation of AuditorAware and override its methods to provide currently logged in user
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Current Logged in User");
    }
}
