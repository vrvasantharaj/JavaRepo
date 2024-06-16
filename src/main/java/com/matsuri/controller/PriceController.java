package com.matsuri.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matsuri.model.Price;
import com.matsuri.service.PriceService;

@RestController
public class PriceController {

	@Autowired
	private PriceService priceService;

	@PostMapping("/publishprice")
	public void publishPrice(@RequestBody Price price) {

		priceService.addPrice(price);

	}

	@GetMapping("/isin/{isinNo}")
	public List<Price> getPriceByIsin(@PathVariable String isinNo) {

		return priceService.getPriceByIsin(isinNo);
	}

	@GetMapping("/vendor/{vendorId}")
	public List<Price> getPriceByVendor(@PathVariable String vendorId) {

		return priceService.getPriceByVendor(vendorId);

	}

}
