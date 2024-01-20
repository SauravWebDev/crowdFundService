package com.main.serv.controller;

import com.main.serv.dtos.AddDonationRequest;
import com.main.serv.dtos.response.AddDonationResponse;
import com.main.serv.exception.ApiException;
import com.main.serv.service.DonationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class DonationControllerTest {

    @InjectMocks
    DonationController donationController;

    @Mock
    DonationService donationService;

    @Test
    public void testAddDonation(){
        AddDonationResponse res = new AddDonationResponse();
        res.setDonationId(123);
        AddDonationRequest addDonationRequest = new AddDonationRequest();
        addDonationRequest.setUserName("user");
        addDonationRequest.setAmount(1011);
        addDonationRequest.setUserDocument("doc");
        addDonationRequest.setProjectId(1);
        addDonationRequest.setDetails("details");
        when(donationService.addDonation(addDonationRequest)).thenReturn(res);
        ResponseEntity<AddDonationResponse> addDonationResponse =  donationController.addDonation(addDonationRequest);
        Assertions.assertEquals(HttpStatus.OK,addDonationResponse.getStatusCode());
    }

    @Test
    public void testAddDonationException(){
        AddDonationRequest addDonationRequest = new AddDonationRequest();
        addDonationRequest.setUserName("user");
        addDonationRequest.setAmount(1011);
        addDonationRequest.setUserDocument("doc");
        addDonationRequest.setProjectId(1);
        addDonationRequest.setDetails("details");
        when(donationService.addDonation(addDonationRequest)).thenThrow(new ApiException(HttpStatus.BAD_REQUEST,"Owner makes invalid donation"));
        ResponseEntity<AddDonationResponse> addDonationResponse = donationController.addDonation(addDonationRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,addDonationResponse.getStatusCode());
    }
}
