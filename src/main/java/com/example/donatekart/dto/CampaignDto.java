package com.example.donatekart.dto;
import java.util.Date;

import lombok.Data;

@Data
public class CampaignDto {
	
	private String code;
	private String title;
	private Boolean featured;
	private Double priority;
	private String shortDesc;
	private String imageSrc;
	private Date created;
	private Date endDate;
	private Double totalAmount;
	private Double procuredAmount;
	private Double totalProcured;
	private Double backersCount;
	private Double categoryId;
	private String ngoCode;
	private String ngoName;
	private Double daysLeft;
	private Double percentage;
}
