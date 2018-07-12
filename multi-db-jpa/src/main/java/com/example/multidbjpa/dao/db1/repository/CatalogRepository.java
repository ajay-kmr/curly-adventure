package com.example.multidbjpa.dao.db1.repository;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends BaseRepository<Catalog, Long> {
}
