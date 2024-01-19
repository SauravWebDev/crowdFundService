package com.main.serv.service;

import com.main.serv.dtos.AddDonationRequest;
import com.main.serv.dtos.response.AddDonationResponse;

public interface DonationService {
    AddDonationResponse addDonation(AddDonationRequest request);
}
