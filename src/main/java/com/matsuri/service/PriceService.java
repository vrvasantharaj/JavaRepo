package com.matsuri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.matsuri.model.Price;
import com.matsuri.repositoy.PriceRepository;

@Service
public class PriceService {
	
	@Autowired
	public PriceRepository priceRepository;

	public void addPrice(Price price) {
        priceRepository.save(price);
    }
	
	public List<Price> getPriceByIsin(String isinNo)
	{
		return priceRepository.getPriceByIsin(isinNo);
				
	}
	
	public List<Price> getPriceByVendor(String vendorId) {
		
		return priceRepository.getPriceByVendor(vendorId);
		
	}
}
