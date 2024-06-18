package com.matsuri.model;

import java.time.LocalDateTime;

public class Price {
	
	public Price(String vendorId, String isinNo, double price, LocalDateTime timestamp) {
		super();
		this.vendorId = vendorId;
		this.isinNo = isinNo;
		this.price = price;
		this.timestamp = timestamp;
	}

	public String vendorId;

	public String isinNo;

	public double price;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getIsinNo() {
		return isinNo;
	}

	public void setIsinNo(String isinNo) {
		this.isinNo = isinNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	private LocalDateTime timestamp;

	
	
}
