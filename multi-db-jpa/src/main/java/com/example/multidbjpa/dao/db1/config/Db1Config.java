package com.example.multidbjpa.dao.db1.config;

import com.example.multidbjpa.dao.db1.entity.Db1EntityMarker;
import com.example.multidbjpa.dao.db1.repository.Db1RepositoryMarker;
import com.example.multidbjpa.dao.db1.repository.Db1UserRepository;
import com.example.multidbjpa.dao.shared.User;
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
@EnableJpaRepositories(entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager", basePackageClasses = Db1RepositoryMarker.class)
public class Db1Config {

    @Autowired
    @Bean
    PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManagerFactory")
                                                             EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }

    @Autowired
    @Bean(name = "db1EntityManagerFactory")
    EntityManagerFactory db1EntityManagerFactory(@Qualifier("db1DataSource") DataSource db1DataSource) {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("db1");
        factoryBean.setDataSource(db1DataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(Db1EntityMarker.class.getPackage().getName(),
                User.class.getPackage().getName());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    /*@Bean
    DataSource db1DataSource() {

        return new EmbeddedDatabaseBuilder().//
                setType(EmbeddedDatabaseType.HSQL).//
                setName("db1s").//
                build();
    }

*/
    @Bean(name = "db1DataSource")
    public DataSource dataSource(@Value("#{applicationConfig.db1DataSource}") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Autowired
    @Bean
    public AuditorAware<User> auditorAware(Db1UserRepository db1UserRepository) {
        return new Db1AuditorAwareImpl(db1UserRepository);
    }

}
