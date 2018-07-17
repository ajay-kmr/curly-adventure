package com.example.multidbjpa.dao.db2.config;

import com.example.multidbjpa.dao.db2.entity.Db2EntityMarker;
import com.example.multidbjpa.dao.db2.repository.Db2RepositoryMarker;
import com.example.multidbjpa.dao.db2.repository.Db2UserRepository;
import com.example.multidbjpa.dao.shared.entity.SharedEntityMarker;
import com.example.multidbjpa.dao.shared.entity.User;
import com.example.multidbjpa.dao.shared.repository.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackageClasses = Db2RepositoryMarker.class,
        repositoryBaseClass = BaseRepositoryImpl.class)
public class Db2Config {

    @Autowired
    @Bean
    PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory")
                                                             EntityManagerFactory db2EntityManagerFactory) {
        return new JpaTransactionManager(db2EntityManagerFactory);
    }

    @Autowired
    @Bean(name = "db2EntityManagerFactory")
    EntityManagerFactory db2EntityManagerFactory(@Qualifier("db2DataSource") DataSource db2DataSource) {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("db2");
        factoryBean.setDataSource(db2DataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(Db2EntityMarker.class.getPackage().getName(),
                SharedEntityMarker.class.getPackage().getName());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
/*
    @Bean
    DataSource db2DataSource() {
        return new EmbeddedDatabaseBuilder().//
                setType(EmbeddedDatabaseType.HSQL).//
                setName("db2s").//
                build();
    }

    */

    @Bean(name = "db2DataSource")
    public DataSource dataSource(@Value("#{applicationConfig.db2DataSource}") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Autowired
    @Bean
    public AuditorAware<User> auditorAware(Db2UserRepository db2UserRepository) {
        return new Db2AuditorAwareImpl(db2UserRepository);
    }

}
