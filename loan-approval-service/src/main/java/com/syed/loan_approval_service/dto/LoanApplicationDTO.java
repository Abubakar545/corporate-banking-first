package com.syed.loan_approval_service.dto;

import lombok.Data;

@Data
public class LoanApplicationDTO {
    private Long id;
    private String applicantName;
    private String status;
    private Double amount;
    private String applicationDetails;

    // Constructors, getters, setters, etc. (if not using Lombok)
}