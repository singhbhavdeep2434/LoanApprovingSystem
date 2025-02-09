package com.bhavdeep.loanapprovalsystem.repository;

import com.bhavdeep.loanapprovalsystem.model.LoanApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoanApplicationRepository extends MongoRepository<LoanApplication, String> {


    Optional<LoanApplication> findById(String Id);
}