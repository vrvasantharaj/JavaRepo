package com.matsuri.repositoy;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.matsuri.model.Price;

@Repository
public interface PriceRepository {
	
	void save(Price price);
	
	List<Price> getPriceByIsin(String isinNo);
	
    List<Price> getPriceByVendor(String vendorId);
}
