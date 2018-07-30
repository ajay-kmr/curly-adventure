package com.example.enverexaample.controller;

import com.example.enverexaample.dao.entity.Customer;
import com.example.enverexaample.dto.DataTableRequestDTO;
import com.example.enverexaample.dto.DataTableResponseDTO;
import com.example.enverexaample.dto.ResponseDTO;
import com.example.enverexaample.services.AuditHistoryService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/audithistory")
@CommonsLog
public class AuditHistoryController {

    @Autowired
    AuditHistoryService auditHistoryService;

    @RequestMapping("customer")
    DataTableResponseDTO findCustomerHistory(@RequestBody DataTableRequestDTO<Long> requestDTO) {
        return auditHistoryService.findCustomerHistory(requestDTO);
    }

    @RequestMapping("customer/{revisionNumber}")
    ResponseDTO<Customer> loadCustomerEntitySnapshotAsOfAGivenRevision(@PathVariable("revisionNumber") Integer revisionNumber) {
        return auditHistoryService.loadCustomerEntitySnapshotAsOfAGivenRevision(revisionNumber);
    }
}
