package com.syed.loan_approval_service.dto;

import lombok.Data;

@Data
public class LoanDisbursementDTO {

    private Long id;
    private Long loanApplicationId;
    private String status;
    private String approvalComments;

}