package com.example.multidbjpa.dao.db1.repository;

import com.example.multidbjpa.dao.db1.entity.Product;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
