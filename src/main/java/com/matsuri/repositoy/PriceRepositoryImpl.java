package com.matsuri.repositoy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.matsuri.model.Price;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

	// private ConcurrentHashMap<String, Double> localStore = new
	// ConcurrentHashMap<>();

	List<Price> lstPrice = new Vector<>();

	@Override
	public void save(Price price) {

		lstPrice.add(
				new Price(price.getVendorId(), price.getIsinNo(), price.getPrice(), LocalDateTime.now()));
		// priceStore.put(price.getVendorId(), lstPrice);
		// LocalDateTime.now().minusDays(40)
	}

	@Override
	public List<Price> getPriceByIsin(String isinNo) {

		return lstPrice.stream().filter(price -> price.getIsinNo().equals(isinNo)).collect(Collectors.toList());
	}

	@Override
	public List<Price> getPriceByVendor(String vendorId) {

		return lstPrice.stream().filter(price -> price.getVendorId().equals(vendorId)).collect(Collectors.toList());

	}

	@Override
	public void removeOldPrices() {

		System.out.println("running removeOldPrices");

		LocalDateTime cutoffTimestamp = LocalDateTime.now().minusDays(30);

		lstPrice.removeIf(price -> price.getTimestamp().isBefore(cutoffTimestamp));

		/*
		 * lstPrice.forEach(price -> System.out.println( price.getVendorId() + " " +
		 * price.getIsinNo() + " " + price.getPrice() + " " + price.getTimestamp()));
		 */

	}

}
