package com.syed.loan_approval_service.service.impl;

import com.syed.loan_approval_service.dto.LoanApplicationDTO;
import com.syed.loan_approval_service.dto.LoanDisbursementDTO;
import com.syed.loan_approval_service.exception.ResourceNotFoundException;
import com.syed.loan_approval_service.model.LoanDisbursement;
import com.syed.loan_approval_service.repository.LoanDisbursementRepository;
import com.syed.loan_approval_service.service.LoanApplicationClient;
import com.syed.loan_approval_service.service.LoanDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService {

    @Autowired
    private LoanDisbursementRepository loanDisbursementRepository;

    @Autowired
    private LoanApplicationClient loanApplicationClient;

    @Override
    public LoanDisbursementDTO submitDisbursement(LoanDisbursementDTO LoanDisbursementDTO) {

        // Fetch the loan application details using Feign Client
        LoanApplicationDTO loanApplicationDTO = loanApplicationClient.getLoanApplicationById(LoanDisbursementDTO.getLoanApplicationId());

        // Perform some operation with the fetched data (example: checking status)
        if (!"Approved".equals(loanApplicationDTO.getStatus())) {
            throw new IllegalStateException("Loan application must be approved before disbursement.");
        }

        LoanDisbursement loanDisbursement = new LoanDisbursement();
        loanDisbursement.setLoanApplicationId(LoanDisbursementDTO.getLoanApplicationId());
        loanDisbursement.setStatus("Pending");
        loanDisbursement.setApprovalComments(LoanDisbursementDTO.getApprovalComments());
        loanDisbursementRepository.save(loanDisbursement);
        LoanDisbursementDTO.setId(loanDisbursement.getId());
        LoanDisbursementDTO.setStatus(loanDisbursement.getStatus());
        return LoanDisbursementDTO;
    }

    @Override
    public List<LoanDisbursementDTO> getAllDisbursements() {
        List<LoanDisbursement> disbursements = loanDisbursementRepository.findAll();
        return disbursements.stream()
                .map(disbursement -> {
                    LoanDisbursementDTO dto = new LoanDisbursementDTO();
                    dto.setId(disbursement.getId());
                    dto.setLoanApplicationId(disbursement.getLoanApplicationId());
                    dto.setStatus(disbursement.getStatus());
                    dto.setApprovalComments(disbursement.getApprovalComments());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public LoanDisbursementDTO approveDisbursement(Long id) throws ResourceNotFoundException {
        LoanDisbursement disbursement = loanDisbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanDisbursement not found for this id :: " + id));
        disbursement.setStatus("Approved");
        loanDisbursementRepository.save(disbursement);
        LoanDisbursementDTO dto = new LoanDisbursementDTO();
        dto.setId(disbursement.getId());
        dto.setLoanApplicationId(disbursement.getLoanApplicationId());
        dto.setStatus(disbursement.getStatus());
        dto.setApprovalComments(disbursement.getApprovalComments());
        return dto;
    }

    @Override
    public LoanDisbursementDTO rejectDisbursement(Long id) throws ResourceNotFoundException {
        LoanDisbursement disbursement = loanDisbursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanDisbursement not found for this id :: " + id));
        disbursement.setStatus("Rejected");
        loanDisbursementRepository.save(disbursement);
        LoanDisbursementDTO dto = new LoanDisbursementDTO();
        dto.setId(disbursement.getId());
        dto.setLoanApplicationId(disbursement.getLoanApplicationId());
        dto.setStatus(disbursement.getStatus());
        dto.setApprovalComments(disbursement.getApprovalComments());
        return dto;
    }
}
