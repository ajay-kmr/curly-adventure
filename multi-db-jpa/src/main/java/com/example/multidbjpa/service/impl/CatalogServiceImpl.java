package com.example.multidbjpa.service.impl;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.db1.repoService.CatalogRepoService;
import com.example.multidbjpa.databinder.CatalogDataBinder;
import com.example.multidbjpa.dto.CatalogDTO;
import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.dto.DataTableResponseDTO;
import com.example.multidbjpa.dto.ResponseDTO;
import com.example.multidbjpa.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    @Autowired
    CatalogRepoService catalogRepoService;

    @Override
    @Transactional
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        ResponseDTO<CatalogDTO> responseDTO = new ResponseDTO<CatalogDTO>(Boolean.FALSE, getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Catalog eg check for duplicate catalog etc
        Catalog catalog = CatalogDataBinder.bind(requestDTO);
        try {
            catalog = catalogRepoService.save(catalog);
            requestDTO.setId(catalog.getId());
            responseDTO.setStatus(Boolean.TRUE);
            responseDTO.setMessage(getMessage("record.successfully.saved"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return catalogRepoService.searchCatalog(requestDTO);
    }
}
