package com.example.multidbjpa.dao.db1.repository;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
