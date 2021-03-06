package com.example.multidbjpa.dao.shared.repoService;

import com.example.multidbjpa.dao.shared.entity.BaseEntity;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.service.impl.BaseServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.criterion.Restrictions.eq;

@CommonsLog
public abstract class BaseRepoService<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl {

    protected abstract BaseRepository<T, ID> getRepository();

    protected Class<T> getEntityClass() {
        return getRepository().getEntityClass();
    }

    protected Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }

    protected EntityManager getEntityManager() {
        return getRepository().getEntityManager();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    protected CriteriaQuery<T> createCriteriaQuery() {
        return getCriteriaBuilder().createQuery(getEntityClass());
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(getEntityClass())
                .add(eq("deleted", false));
    }

    protected Criteria addPagingAndSorting(Criteria criteria, DataTableRequestDTO dataTableSearchDTO) {
        addPagingAndSorting(criteria, dataTableSearchDTO.getMax(), dataTableSearchDTO.getOffset(),
                dataTableSearchDTO.getOrderCriteria());
        return criteria;
    }

    private void addPagingAndSorting(Criteria criteria, int max, int offset, Order order) {
        criteria.setFirstResult(offset)
                .setMaxResults(max)
                .addOrder(order);
    }

    public long count() {
        return getRepository().count();
    }

    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    public Long count(Criteria criteria) {
        criteria.setFirstResult(0);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public <S extends T> boolean exists(Example<S> example) {
        return getRepository().exists(example);
    }

    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    public Optional<T> findOne(ID id) {
        if (id == null) {
            return Optional.empty();
        }
        return getRepository().findById(id);
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return getRepository().findOne(example);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public void flush() {
        getRepository().flush();
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        if (entities != null) {
            return getRepository().saveAll(entities);
        }
        return null;
    }

    public void delete(ID id) {
        if (id != null) {
            Optional<T> entity = getRepository().findById(id);
            entity.ifPresent(this::delete);
        }
        //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(id);

    }

    public void delete(T entity) {
        if (entity != null) {
            //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(entity);
            entity.setDeleted(true);
            save(entity);
        }
    }

    public void delete(Iterable<? extends T> entities) {
        if (entities != null) {
            //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().delete(entities);
            entities.forEach(it -> it.setDeleted(true));
            save(entities);
        }
    }

    public void deleteAll() {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteAll();
        throw new IllegalArgumentException("Delete All is restricted..");
    }

    public void deleteInBatch(Iterable<T> entities) {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteInBatch(entities);
        throw new IllegalArgumentException("DeleteInBatch is restricted..");
    }

    public void deleteAllInBatch() {
        //TODO Discuss whether this should be har delete or soft delete.
        getRepository().deleteAllInBatch();
    }

    public List<ID> findValidIds(List<ID> idsForValidation) {
        idsForValidation = idsForValidation.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(idsForValidation)) {
            return Collections.emptyList();
        }
        List<T> result = getRepository().findAllById(idsForValidation);
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        return result.stream().map((T it) -> (ID) it.getId()).collect(Collectors.toList());
    }

    public List<T> findAllById(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        getRepository().deleteAll(entities);
    }

    public Optional<T> findOne(@Nullable Specification<T> spec) {
        return getRepository().findOne(spec);
    }

    public List<T> findAll(@Nullable Specification<T> spec) {
        return getRepository().findAll(spec);
    }

    public Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable) {
        return getRepository().findAll(spec, pageable);
    }

    public List<T> findAll(@Nullable Specification<T> spec, Sort sort) {
        return getRepository().findAll(spec, sort);
    }

    public long count(@Nullable Specification<T> spec) {
        return getRepository().count(spec);
    }
}
