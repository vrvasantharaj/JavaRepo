package com.matsuri.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Void> publishPrice(@RequestBody Price price) {
		
		try {
			priceService.addPrice(price);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/isin/{isinNo}")
	public ResponseEntity<List<Price>> getPriceByIsin(@PathVariable String isinNo) {

        try {
			List<Price> prices = priceService.getPriceByIsin(isinNo);

			return new ResponseEntity<>(prices, HttpStatus.OK);
		} catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		} 
	}

	@GetMapping("/vendor/{vendorId}")
	public  ResponseEntity<List<Price>> getPriceByVendor(@PathVariable String vendorId) {

		try {
			List<Price> prices = priceService.getPriceByVendor(vendorId);
			return new ResponseEntity<>(prices, HttpStatus.OK);

		} catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	

}
