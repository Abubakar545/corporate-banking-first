package com.syed.loan_approval_service.service;

import com.syed.loan_approval_service.dto.LoanApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "loan-application-service")
public interface LoanApplicationClient {

    @GetMapping("/api/loans/{id}")
    LoanApplicationDTO getLoanApplicationById(@PathVariable("id") Long id);
}
