package together.pet.domain.member.dto;

import java.sql.Date;

import lombok.Data;

/**
 * 예약 DTO
 */
@Data
public class myReserve {
	private int resNum;
	private String resDate;
	private int resPeoples;
	private String id;
	private int facNum;
	private String facName;
	private String facCategories;
	private String facCity;
	private String facContry;
	private double latitute;
	private double longitute;
	private String addRoad;
	private String addBasic;
	private String facTel;
	private String facWebsite;
	private String facHoliday;
	private String runningtime;
	private String parking;
	private String admissionFee;
	private String petInfo;
	private String petSize;
	private String petLimit;
	private String withIn;
	private String withOut;
	private String categories;
	private String surcharge;
}
