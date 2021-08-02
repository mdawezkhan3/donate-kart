package com.example.donatekart.service;

import org.springframework.stereotype.Service;

import com.example.donatekart.dto.CampaignDto;
import com.example.donatekart.dto.SortedCampaignDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {


	
    public List<CampaignDto> findAll(String campaigns) throws IOException {
    	
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CampaignDto>>() {}.getType();
        List<CampaignDto> list = gson.fromJson(campaigns, listType);

        return list;
    }
    
    public List<SortedCampaignDto> sort(List<CampaignDto> campaigns) {
    	campaigns.sort((o1, o2)
                -> o2.getTotalAmount().compareTo(
                		o1.getTotalAmount()));
    	
         return campaigns.stream().map(campaign -> {
        	 return SortedCampaignDto.valueOf(campaign);
        }).collect(Collectors.toList());
    }
    
    
    public List<CampaignDto> filterActiveCampaigns(List<CampaignDto> campaigns) {
    	
    	Date today = new Date();
    	campaigns = campaigns
                .stream()
                .filter(c -> c.getEndDate().compareTo(today) >= 0)
                .collect(Collectors.toList());
    	
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(today);
    	cal.add(Calendar.DAY_OF_MONTH, -30);
    	Date today30 = cal.getTime();
    	System.out.println(today30);
    	System.out.println(today);
    	campaigns = campaigns
                .stream()
                .filter(c -> c.getCreated().compareTo(today30) >= 0)
                .collect(Collectors.toList());    	
    	
    	
    	return campaigns;
    }
    
    
    public List<CampaignDto> filterClosedCampaigns(List<CampaignDto> campaigns) {
    	
    	Date today = new Date();
    	campaigns = campaigns
                .stream()
                .filter(c -> ((c.getEndDate().compareTo(today) < 0) || (c.getProcuredAmount() >= c.getTotalAmount())))
                .collect(Collectors.toList());
    	
    	return campaigns;
    	
    }
    
}
