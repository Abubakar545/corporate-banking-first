package com.syed.loan_application_service.repository;

import com.syed.loan_application_service.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}