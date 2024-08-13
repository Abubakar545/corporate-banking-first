package com.syed.loan_approval_service.controller;

import com.syed.loan_approval_service.dto.LoanApplicationDTO;
import com.syed.loan_approval_service.dto.LoanDisbursementDTO;
import com.syed.loan_approval_service.exception.ResourceNotFoundException;
import com.syed.loan_approval_service.service.LoanApplicationClient;
import com.syed.loan_approval_service.service.LoanDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-disbursement")
public class LoanDisbursementController {
    @Autowired
    private LoanApplicationClient loanApplicationClient;

    @Autowired
    private LoanDisbursementService loanDisbursementService;


    @GetMapping("/fetch-application/{id}")
    public LoanApplicationDTO fetchLoanApplicationById(@PathVariable("id") Long id) {
        // Use Feign Client to fetch the loan application details
        return loanApplicationClient.getLoanApplicationById(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<LoanDisbursementDTO> submitDisbursement(@RequestBody LoanDisbursementDTO LoanDisbursementDTO) {
        LoanDisbursementDTO createdDisbursement = loanDisbursementService.submitDisbursement(LoanDisbursementDTO);
        return ResponseEntity.ok(createdDisbursement);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanDisbursementDTO>> getAllDisbursements() {
        List<LoanDisbursementDTO> disbursements = loanDisbursementService.getAllDisbursements();
        return ResponseEntity.ok(disbursements);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<LoanDisbursementDTO> approveDisbursement(@PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO approvedDisbursement = loanDisbursementService.approveDisbursement(id);
        return ResponseEntity.ok(approvedDisbursement);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<LoanDisbursementDTO> rejectDisbursement(@PathVariable Long id) throws ResourceNotFoundException {
        LoanDisbursementDTO rejectedDisbursement = loanDisbursementService.rejectDisbursement(id);
        return ResponseEntity.ok(rejectedDisbursement);
    }
}
