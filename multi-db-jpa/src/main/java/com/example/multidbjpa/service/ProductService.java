package com.example.multidbjpa.service;


import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.dto.DataTableResponseDTO;
import com.example.multidbjpa.dto.ProductDTO;
import com.example.multidbjpa.dto.ResponseDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO);

    ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO);

    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO);
}
