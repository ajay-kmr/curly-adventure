package com.example.multidbjpa.dao.db1.repoService;

import com.example.multidbjpa.dao.db1.entity.Catalog;
import com.example.multidbjpa.dao.db1.entity.Catalog_;
import com.example.multidbjpa.dao.db1.repository.CatalogRepository;
import com.example.multidbjpa.dao.db1.specification.CatalogSpecifications;
import com.example.multidbjpa.dao.shared.repoService.BaseRepoService;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import com.example.multidbjpa.dto.CatalogDTO;
import com.example.multidbjpa.dto.DataTableRequestDTO;
import com.example.multidbjpa.dto.DataTableResponseDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@CommonsLog
public class CatalogRepoService extends BaseRepoService<Catalog, Long> {

    @Autowired
    CatalogRepository catalogRepository;

    @Override
    protected BaseRepository<Catalog, Long> getRepository() {
        return catalogRepository;
    }

    @SuppressWarnings("unchecked")
    public DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        Criteria criteria = getCriteria();
        CatalogDTO catalogDTO = requestDTO.getQuery();
        if (catalogDTO != null) {
            if (catalogDTO.getId() != null) {
                criteria.add(Restrictions.eq(Catalog_.ID, catalogDTO.getId()));
            }
            if (!StringUtils.isEmpty(catalogDTO.getName())) {
                criteria.add(Restrictions.ilike(Catalog_.NAME, catalogDTO.getName(), MatchMode.ANYWHERE));
            }
        }
        Long filteredFrom = count(criteria);
        addPagingAndSorting(criteria, requestDTO);
        addProjection(criteria);
        criteria.setResultTransformer(Transformers.aliasToBean(CatalogDTO.class));
        List<CatalogDTO> criteriaResult = (List<CatalogDTO>) criteria.list();
        return DataTableResponseDTO.getInstance(count(), criteriaResult, filteredFrom, requestDTO);
    }

    private void addProjection(Criteria criteria) {
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property(Catalog_.ID).as("id"))
                .add(Projections.property(Catalog_.NAME).as("name"))
        );
    }

    public DataTableResponseDTO<Object, List<Catalog>> findByNameLike(String name) {
        Page<Catalog> catalogPage = catalogRepository.findAll(CatalogSpecifications.nameLike(name), PageRequest.of(0, 10, Sort.Direction.ASC, Catalog_.ID));
        return DataTableResponseDTO.of(catalogPage);
    }
}
