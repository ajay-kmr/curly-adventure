package com.example.multidbjpa.service;


import com.example.multidbjpa.dto.CatalogDTO;
import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.dto.DataTableResponseDTO;
import com.example.multidbjpa.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
