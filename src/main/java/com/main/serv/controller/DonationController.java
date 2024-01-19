package com.main.serv.controller;

import com.main.serv.dtos.AddDonationRequest;
import com.main.serv.dtos.response.AddDonationResponse;
import com.main.serv.exception.ApiException;
import com.main.serv.service.DonationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/donation")
public class DonationController {
    private DonationService donationService;
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }
    @PostMapping(value = {"/"})
    public ResponseEntity<AddDonationResponse> addDonation(@Valid @RequestBody AddDonationRequest donation){
        try {
            return ResponseEntity.ok(donationService.addDonation(donation));
        }catch (ApiException e) {
            AddDonationResponse addDonationResponse = new AddDonationResponse();
            addDonationResponse.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(addDonationResponse, e.getStatus());
        }
    }}
