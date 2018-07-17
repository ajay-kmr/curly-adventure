package com.example.multidbjpa.dao.shared.repository;

import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

//Ideally It should not be marked with @NoRepositoryBean
// but rather it should be marked with @Repository bean.
// But its giving error. So fixing this issue after referring @link https://stackoverflow.com/questions/44831103/spring-boot-jpa-unable-to-add-custom-repository/
@NoRepositoryBean
//@Repository
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    //Please read- @link https://stackoverflow.com/questions/44831103/spring-boot-jpa-unable-to-add-custom-repository/

    EntityManager em;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    public Class<T> getEntityClass() {
        return getDomainClass();
    }

    public Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<T> getDomainClass() {
        return super.getDomainClass();
    }
}
