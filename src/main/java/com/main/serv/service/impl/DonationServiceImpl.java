package com.main.serv.service.impl;

import com.main.serv.dtos.AddDonationRequest;
import com.main.serv.dtos.response.AddDonationResponse;
import com.main.serv.entity.Donation;
import com.main.serv.entity.Project;
import com.main.serv.exception.ApiException;
import com.main.serv.repository.DonationRepository;
import com.main.serv.repository.ProjectRepository;
import com.main.serv.service.DonationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {
    private final ProjectRepository projectRepository;

    private final DonationRepository donationRepository;

    public DonationServiceImpl(ProjectRepository projectRepository, DonationRepository donationRepository) {
        this.projectRepository = projectRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public AddDonationResponse addDonation(AddDonationRequest request) {
        if(!projectRepository.existsById(request.getProjectId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Project does not exist");
        }
        Project project = projectRepository.findById(request.getProjectId()).get();
        if(!project.getStatus().equals("ACTIVE")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Project does not take donation");
        }
        int donationAmount = request.getAmount();
        int refundAmount = 0;
        if(project.getAmountRaised() + donationAmount
                >= project.getAmountGoal()) {
            donationAmount = project.getAmountGoal() - project.getAmountRaised();
            refundAmount = request.getAmount() - donationAmount;
            project.setAmountRaised(project.getAmountGoal());
            project.setStatus("ARCHIVE");
        }else {
            project.setAmountRaised(project.getAmountRaised() + donationAmount);
        }

        Donation donation = new Donation();
        donation.setUserName(request.getUserName());
        donation.setAmount(donationAmount);
        donation.setProjectId(request.getProjectId());
        donation.setDetails(request.getDetails());
        donation.setUserDocumentId(request.getUserDocument());
        // donation status can be initiated , Inprogress, Completed, Failed, REFUNDED
        donation.setStatus("COMPLETE");
        donationRepository.save(donation);
        projectRepository.save(project);
        // processing refund
        if(refundAmount > 0) {
            // TODO - update project as archive
            donation.setAmount(refundAmount);
            donation.setStatus("REFUND");
            donationRepository.save(donation);
        }
        // Fix Response
        AddDonationResponse res = new AddDonationResponse();
        res.setDonationId(123);
        return res;
    }
}
