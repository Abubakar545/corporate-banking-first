package com.syed.loan_application_service.controller;


import com.syed.loan_application_service.dto.LoanApplicationDTO;
import com.syed.loan_application_service.dto.LoanApprovalDTO;
import com.syed.loan_application_service.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationDTO> getLoanApplicationById(@PathVariable("id") Long id) {
        LoanApplicationDTO loanApplicationDTO = loanApplicationService.getLoanApplicationById(id);
        if (loanApplicationDTO != null) {
            return ResponseEntity.ok(loanApplicationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/apply", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoanApplicationDTO> submitLoanApplication(@RequestBody LoanApplicationDTO loanApplicationDTO) {
        return ResponseEntity.ok(loanApplicationService.submitApplication(loanApplicationDTO));
    }

    @GetMapping(value = "/status/{id}", produces = "application/json")
    public ResponseEntity<LoanApplicationDTO> getApplicationStatus(@PathVariable Long id) {
        return ResponseEntity.ok(loanApplicationService.getApplicationStatus(id));
    }

    @PostMapping(value = "/review", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoanApplicationDTO> reviewApplication(@RequestBody LoanApprovalDTO loanApprovalDTO) {
        return ResponseEntity.ok(loanApplicationService.reviewApplication(loanApprovalDTO));
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<LoanApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(loanApplicationService.getAllApplications());
    }
}
