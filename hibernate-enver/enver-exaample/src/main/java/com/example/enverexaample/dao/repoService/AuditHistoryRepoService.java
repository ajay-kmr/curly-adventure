package com.example.enverexaample.dao.repoService;

import com.example.enverexaample.dao.entity.Customer;
import com.example.enverexaample.dto.DataTableRequestDTO;
import com.example.enverexaample.dto.DataTableResponseDTO;
import com.example.enverexaample.dto.ResponseDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@CommonsLog
public class AuditHistoryRepoService {

    @PersistenceContext
    EntityManager entityManager;

    public DataTableResponseDTO findCustomerHistory(DataTableRequestDTO<Long> requestDTO) {
        List<Customer> posts = AuditReaderFactory.get(entityManager)
                .createQuery()
                .forRevisionsOfEntity(Customer.class, true, true)
                .add(AuditEntity.id().eq(requestDTO.getQuery()))
                .addOrder(AuditEntity.property(requestDTO.getSortColumn()).desc())
                .setFirstResult(requestDTO.getOffset())
                .setMaxResults(requestDTO.getMax())
                .getResultList();

        for (int i = 0; i < posts.size(); i++) {
            log.info(String.format("Revision {%s} of Customer entity: {%s}", i + 1, posts.get(i)));
        }
        DataTableResponseDTO dataTableResponseDTO = new DataTableResponseDTO();
        dataTableResponseDTO.setData(posts);
        return dataTableResponseDTO;
    }

    public ResponseDTO<Customer> loadCustomerEntitySnapshotAsOfAGivenRevision(Integer revisionNumber) {
        Customer customer = (Customer) AuditReaderFactory.get(entityManager)
                .createQuery()
                .forEntitiesAtRevision(Customer.class, revisionNumber)
                .getSingleResult();

        log.info(String.format("Customer 1st revision:- %s", customer.getFirstName()));
        return new ResponseDTO<>(Boolean.TRUE, "Snapshot fetched successfully", customer);
    }
}
