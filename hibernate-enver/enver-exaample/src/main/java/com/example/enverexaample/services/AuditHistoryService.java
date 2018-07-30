package com.example.enverexaample.services;

import com.example.enverexaample.dao.entity.Customer;
import com.example.enverexaample.dao.repoService.AuditHistoryRepoService;
import com.example.enverexaample.dto.DataTableRequestDTO;
import com.example.enverexaample.dto.DataTableResponseDTO;
import com.example.enverexaample.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditHistoryService {
    @Autowired
    AuditHistoryRepoService auditHistoryRepoService;

    public DataTableResponseDTO findCustomerHistory(DataTableRequestDTO<Long> requestDTO) {
        return auditHistoryRepoService.findCustomerHistory(requestDTO);
    }

    public ResponseDTO<Customer> loadCustomerEntitySnapshotAsOfAGivenRevision(Integer revisionNumber) {
        return auditHistoryRepoService.loadCustomerEntitySnapshotAsOfAGivenRevision(revisionNumber);
    }
}
