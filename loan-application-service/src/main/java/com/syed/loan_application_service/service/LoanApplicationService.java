package com.syed.loan_application_service.service;

import com.syed.loan_application_service.dto.LoanApplicationDTO;
import com.syed.loan_application_service.dto.LoanApprovalDTO;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationDTO submitApplication(LoanApplicationDTO loanApplicationDTO);
    LoanApplicationDTO getApplicationStatus(Long id);
    LoanApplicationDTO reviewApplication(LoanApprovalDTO loanApprovalDTO);
    List<LoanApplicationDTO> getAllApplications();
    LoanApplicationDTO getLoanApplicationById(Long id);
}