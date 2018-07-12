package com.example.multidbjpa.service.impl;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.db1.entity.Product;
import com.example.multidbjpa.dao.db1.repoService.CatalogRepoService;
import com.example.multidbjpa.dao.db1.repoService.ProductRepoService;
import com.example.multidbjpa.databinder.ProductDataBinder;
import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.dto.DataTableResponseDTO;
import com.example.multidbjpa.dto.ProductDTO;
import com.example.multidbjpa.dto.ResponseDTO;
import com.example.multidbjpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

    @Autowired
    ProductRepoService productRepoService;

    @Autowired
    CatalogRepoService catalogRepoService;

    @Override
    @Transactional
    public ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Optional<Catalog> catalogOptional = catalogRepoService.findOne(requestDTO.getCatalogId());
        if (catalogOptional.isPresent()) {
            responseDTO.setMessage(getMessage("invalid.catalog.id"));
        } else {
            Product product = ProductDataBinder.bind(requestDTO, catalogOptional.get());
            try {
                product = productRepoService.save(product);
                requestDTO.setId(product.getId());
                responseDTO.setMessage(getMessage("record.successfully.saved"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, getMessage("unable.to.remove.catalog.product"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Optional<Product> product = productRepoService.findOne(requestDTO.getId());
        if (product.isPresent()) {
            responseDTO.setMessage(getMessage("invalid.product.id"));
        } else {
            try {
                Product product1 = product.get();
                product1.setDeleted(Boolean.TRUE);
                product1 = productRepoService.save(product1);
                responseDTO.setMessage(getMessage("record.deleted.successfully"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    public DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        return productRepoService.searchProduct(requestDTO);
    }
}
