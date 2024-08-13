package com.syed.loan_application_service.dto;


import com.syed.loan_application_service.model.LoanApplication;
import lombok.Data;

@Data
public class LoanApprovalDTO {
    private Long applicationId;
    private String approvalStatus;
    private String officerComments;


    public LoanApprovalDTO(){

    }

    public LoanApprovalDTO(LoanApplication loanApplication){
        this.applicationId = loanApplication.getId();
        this.approvalStatus = loanApplication.getStatus();
        this.officerComments = loanApplication.getApplicationDetails();
    }

}
