package com.syed.loan_application_service.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "loan_application")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicantName;
    private double amount;
    private String status;
    private String applicationDetails;

}