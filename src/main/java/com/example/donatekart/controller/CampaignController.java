package com.example.donatekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;
import com.example.donatekart.dto.CampaignDto;
import com.example.donatekart.dto.SortedCampaignDto;
import com.example.donatekart.service.CampaignService;



@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
public class CampaignController {
    private final CampaignService campaignService;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    
    @GetMapping(value = "/sorted-list-of-campaigns", produces = "application/json")
    public ResponseEntity<List<SortedCampaignDto>> getListOfCampaigns() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        String campaigns = restTemplate.getForObject("https://testapi.donatekart.com/api/campaign", String.class);
        List<CampaignDto> campaignlist = campaignService.findAll(campaigns);
        List<SortedCampaignDto> sortedCampaigns = campaignService.sort(campaignlist);
        
        headers.set("Content-Range", "SortedCampaigns 0-" + (sortedCampaigns.size() - 1) + "/" + sortedCampaigns.size());
        return ResponseEntity
                .status(200)
                .headers(headers)
                .body(sortedCampaigns);
    }
    
    @GetMapping(value = "/active-campaigns", produces = "application/json")
    public ResponseEntity<List<CampaignDto>> getActiveCampaigns() throws IOException {
    	
        HttpHeaders headers = new HttpHeaders();
        String campaigns = restTemplate.getForObject("https://testapi.donatekart.com/api/campaign", String.class);
        List<CampaignDto> campaignlist = campaignService.findAll(campaigns);
        List<CampaignDto> activeCampaigns = campaignService.filterActiveCampaigns(campaignlist);
        headers.set("Content-Range", "CampaignDtos 0-" + (activeCampaigns.size() - 1) + "/" + activeCampaigns.size());
        return ResponseEntity
                .status(200)
                .headers(headers)
                .body(activeCampaigns);
    }
    
    
    @GetMapping(value = "/closed-campaigns", produces = "application/json")
    public ResponseEntity<List<CampaignDto>> getClosedCampaigns() throws IOException {
    	
        HttpHeaders headers = new HttpHeaders();
        String campaigns = restTemplate.getForObject("https://testapi.donatekart.com/api/campaign", String.class);
        List<CampaignDto> campaignlist = campaignService.findAll(campaigns);
        List<CampaignDto> closedCampaigns = campaignService.filterClosedCampaigns(campaignlist);
        headers.set("Content-Range", "CampaignDtos 0-" + (closedCampaigns.size() - 1) + "/" + closedCampaigns.size());
        return ResponseEntity
                .status(200)
                .headers(headers)
                .body(closedCampaigns);
    }
}
