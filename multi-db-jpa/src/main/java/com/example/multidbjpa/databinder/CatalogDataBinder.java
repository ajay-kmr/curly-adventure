package com.example.multidbjpa.databinder;


import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dto.CatalogDTO;

public class CatalogDataBinder {

    public static Catalog bind(CatalogDTO source) {
        if (source == null) {
            return null;
        }
        return bind(new Catalog(), source);
    }

    public static Catalog bind(Catalog destination, CatalogDTO source) {
        if (source == null) {
            return null;
        }
        destination.setId(source.getId());
        destination.setName(source.getName());
        return destination;
    }
}
