package com.syed.loan_application_service.dto;


import com.syed.loan_application_service.model.LoanApplication;
import lombok.Data;

@Data
public class LoanApplicationDTO {
    private Long id;
    private String applicantName;
    private String status;
    private Double amount;
    private String applicationDetails;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(LoanApplication loanApplication) {
        this.id = loanApplication.getId();
        this.applicantName = loanApplication.getApplicantName();
        this.status = loanApplication.getStatus();
        this.amount = loanApplication.getAmount();
        this.applicationDetails = loanApplication.getApplicationDetails();
    }
}
