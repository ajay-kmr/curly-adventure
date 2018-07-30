package com.example.enverexaample.config;


import com.example.enverexaample.dao.entity.EntityMarker;
import com.example.enverexaample.dao.repository.RepositoryMarker;
import com.example.enverexaample.envers.CustomRevisionEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@CommonsLog
@Configuration
@EnableTransactionManagement
@EntityScan(basePackageClasses = {EntityMarker.class})
//@EntityScan(basePackageClasses = {EntityMarker.class, CustomRevisionEntity.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryMarker.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//@PropertySource(value = {"classpath:config/application-core.yml"})
public class JPAConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
