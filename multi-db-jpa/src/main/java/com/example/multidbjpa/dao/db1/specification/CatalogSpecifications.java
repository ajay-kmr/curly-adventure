package com.example.multidbjpa.dao.db1.specification;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.db1.entity.Catalog_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CatalogSpecifications {

    public static Specification<Catalog> nameLike(String name) {
        return (Root<Catalog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.like(root.get(Catalog_.name), name);
    }
}
