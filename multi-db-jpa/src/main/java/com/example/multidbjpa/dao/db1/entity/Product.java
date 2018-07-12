package com.example.multidbjpa.dao.db1.entity;

import com.example.multidbjpa.dao.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Product extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    private String name;

    private String type;

    @ManyToOne
    private Catalog catalog;


}
