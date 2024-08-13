package com.syed.loan_approval_service.service;

import com.syed.loan_approval_service.dto.LoanDisbursementDTO;
import com.syed.loan_approval_service.exception.ResourceNotFoundException;

import java.util.List;

public interface LoanDisbursementService {
    LoanDisbursementDTO submitDisbursement(LoanDisbursementDTO LoanDisbursementDTO);
    List<LoanDisbursementDTO> getAllDisbursements();
    LoanDisbursementDTO approveDisbursement(Long id) throws ResourceNotFoundException;
    LoanDisbursementDTO rejectDisbursement(Long id) throws ResourceNotFoundException;
}