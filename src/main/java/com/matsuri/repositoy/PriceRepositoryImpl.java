package com.matsuri.repositoy;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.matsuri.model.Price;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

	// private ConcurrentHashMap<String, Double> localStore = new
	// ConcurrentHashMap<>();

	//private ConcurrentHashMap<String, List<Price>> priceStore = new ConcurrentHashMap<>();

	List<Price> lstPrice = new Vector<>();

	@Override
	public void save(Price price) {
		
		// localStore.put(price.getIsinNo(), price.getPrice() );

		lstPrice.add(new Price(price.getVendorId(), price.getIsinNo(), price.getPrice()));

		//localStore1.put(price.getIsinNo(), k -> new ArrayList<>.price());		
		//priceStore.put(price.getVendorId(), lstPrice);
		//priceStore.put()

	}

	/*@Override
	public List<Double> getPriceByIsin(String isinNo) {
		
		 * List<Double> lstPrice = new ArrayList<Double>();
		 * //System.out.println("isinNo:" + isinNo);
		 * lstPrice.add(localStore.get(isinNo));
		 * 
		 * for(int i=0;i<lstPrice.size();i++){ System.out.println(lstPrice.get(i)); }
		 * 
		 * return lstPrice;
		 
	}*/
	
	@Override
	public List<Price> getPriceByIsin(String isinNo) {

		return lstPrice.stream().filter(price -> price.getIsinNo().equals(isinNo)).collect(Collectors.toList());

	}

	@Override
	public List<Price> getPriceByVendor(String vendorId) {
		
		return lstPrice.stream().filter(price -> price.getVendorId().equals(vendorId)).collect(Collectors.toList());

	}

}
