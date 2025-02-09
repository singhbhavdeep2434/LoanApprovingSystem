package com.bhavdeep.loanapprovalsystem.controller;
import com.bhavdeep.loanapprovalsystem.model.LoanApplication;
import com.bhavdeep.loanapprovalsystem.repository.LoanApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/loan")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @GetMapping("/apply")
    public String showApplicationForm(Model model) {
        model.addAttribute("loanApplication", new LoanApplication());
        return "apply";
    }

    @PostMapping("/submit")
    public String processApplication(@Valid LoanApplication loanApplication, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "apply";
        }

        if (loanApplication.getCreditScore() >= 700) {
            loanApplication.setStatus("Approved");
        } else if (loanApplication.getCreditScore() < 500) {
            loanApplication.setStatus("Rejected");
        } else {
            loanApplication.setStatus("Pending");
        }

        loanApplicationRepository.save(loanApplication);
        model.addAttribute("loanApplication", loanApplication);
        return "status";
    }

    @GetMapping("/status/{id}")
    public String viewApplicationStatus(@PathVariable String id, Model model) {
        Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(id);
        model.addAttribute("loanApplication", loanApplication);
        return "status";
    }
}