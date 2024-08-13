package com.syed.loan_application_service.service.impl;


import com.syed.loan_application_service.dto.LoanApplicationDTO;
import com.syed.loan_application_service.dto.LoanApprovalDTO;
import com.syed.loan_application_service.exception.ResourceNotFoundException;
import com.syed.loan_application_service.model.LoanApplication;
import com.syed.loan_application_service.repository.LoanApplicationRepository;
import com.syed.loan_application_service.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public LoanApplicationDTO submitApplication(LoanApplicationDTO loanApplicationDTO) {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicantName(loanApplicationDTO.getApplicantName());
        loanApplication.setAmount(loanApplicationDTO.getAmount());
        loanApplication.setStatus("Pending");
        loanApplication.setApplicationDetails(loanApplicationDTO.getApplicationDetails());

        LoanApplication savedApplication = loanApplicationRepository.save(loanApplication);
        return convertToDTO(savedApplication);
    }

    @Override
    public LoanApplicationDTO getLoanApplicationById(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this id :: " + id));
        return convertToDTO(loanApplication);
    }


    @Override
    public LoanApplicationDTO getApplicationStatus(Long id) {
        LoanApplication loanApplication = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this id :: " + id));
        return convertToDTO(loanApplication);
    }

    @Override
    public LoanApplicationDTO reviewApplication(LoanApprovalDTO loanApprovalDTO) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApprovalDTO.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this id :: " + loanApprovalDTO.getApplicationId()));

        loanApplication.setStatus(loanApprovalDTO.getApprovalStatus());
//        loanApplication.setApplicationDetails(loanApprovalDTO.getOfficerComments());
        LoanApplication updatedApplication = loanApplicationRepository.save(loanApplication);
        return convertToDTO(updatedApplication);
    }

    @Override
    public List<LoanApplicationDTO> getAllApplications() {
        return loanApplicationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LoanApplicationDTO convertToDTO(LoanApplication loanApplication) {
        LoanApplicationDTO dto = new LoanApplicationDTO();
        dto.setId(loanApplication.getId());
        dto.setApplicantName(loanApplication.getApplicantName());
        dto.setAmount(loanApplication.getAmount());
        dto.setStatus(loanApplication.getStatus());
        dto.setApplicationDetails(loanApplication.getApplicationDetails());
        return dto;
    }
}