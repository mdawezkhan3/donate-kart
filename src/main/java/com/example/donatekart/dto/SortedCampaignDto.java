package com.example.donatekart.dto;
import java.util.Date;

import lombok.Data;

@Data
public class SortedCampaignDto {
	
	private String title;
	private Double totalAmount;
	private Double backersCount;
	private Date endDate;
	
	public static SortedCampaignDto valueOf(CampaignDto campaignDto) {
		SortedCampaignDto sortedCampaignDto = new SortedCampaignDto();
		sortedCampaignDto.setTitle(campaignDto.getTitle());
		sortedCampaignDto.setTotalAmount(campaignDto.getTotalAmount());
		sortedCampaignDto.setBackersCount(campaignDto.getBackersCount());
		sortedCampaignDto.setEndDate(campaignDto.getEndDate());
		return sortedCampaignDto;
	}

}