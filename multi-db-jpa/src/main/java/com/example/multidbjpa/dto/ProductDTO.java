package com.example.multidbjpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseEntityDTO<Long> {
    protected Long id;

    private String name;

    private String type;

    protected Long catalogId;

    private String catalogName;

}
