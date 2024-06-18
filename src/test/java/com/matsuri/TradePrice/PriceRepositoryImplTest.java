package com.matsuri.TradePrice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.matsuri.model.Price;
import com.matsuri.repositoy.PriceRepositoryImpl;

@SpringBootTest
public class PriceRepositoryImplTest {

    private PriceRepositoryImpl priceRepository;

    @BeforeEach
    public void setUp() {
        priceRepository = new PriceRepositoryImpl();
    }

    @Test
    public void testSave() {
        Price price = new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now());
        priceRepository.save(price);

        List<Price> prices = priceRepository.getPriceByVendor("BLOOMBERG");
        assertEquals(1, prices.size());
        assertEquals("BLOOMBERG", prices.get(0).getVendorId());
        assertEquals("US-000402625-0", prices.get(0).getIsinNo());
        assertEquals(100.0, prices.get(0).getPrice());
    }

    @Test
    public void testGetPriceByIsin() {
        Price price1 = new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now());
        Price price2 = new Price("REFINITIV", "US-000402625-0", 200.0, LocalDateTime.now());
        Price price3 = new Price("BLOOMBERG", "UK-000402625-0", 300.0, LocalDateTime.now());
        
        priceRepository.save(price1);
        priceRepository.save(price2);
        priceRepository.save(price3);

        List<Price> prices = priceRepository.getPriceByIsin("US-000402625-0");
        assertEquals(2, prices.size());
        assertTrue(prices.stream().anyMatch(price -> price.getVendorId().equals("BLOOMBERG") && price.getPrice() == 100.0));
        assertTrue(prices.stream().anyMatch(price -> price.getVendorId().equals("REFINITIV") && price.getPrice() == 200.0));
    }

    @Test
    public void testGetPriceByVendor() {
        Price price1 = new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now());
        Price price2 = new Price("BLOOMBERG", "UK-000402625-0", 200.0, LocalDateTime.now());
        Price price3 = new Price("REFINITIV", "US-000402625-0", 300.0, LocalDateTime.now());
        
        priceRepository.save(price1);
        priceRepository.save(price2);
        priceRepository.save(price3);

        List<Price> prices = priceRepository.getPriceByVendor("BLOOMBERG");
        assertEquals(2, prices.size());
        assertTrue(prices.stream().anyMatch(price -> price.getIsinNo().equals("US-000402625-0") && price.getPrice() == 100.0));
        assertTrue(prices.stream().anyMatch(price -> price.getIsinNo().equals("UK-000402625-0") && price.getPrice() == 200.0));
    }

    @Test
    public void testRemoveOldPrices() {
        Price recentPrice = new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now().minusDays(10));
        Price oldPrice = new Price("BLOOMBERG", "US-000402625-0", 200.0, LocalDateTime.now().minusDays(40));

        priceRepository.save(recentPrice);
        priceRepository.save(oldPrice);

        priceRepository.removeOldPrices();

        List<Price> prices = priceRepository.getPriceByVendor("BLOOMBERG");
        assertEquals(100.0, prices.get(0).getPrice());       
		
        /*prices.forEach(price -> System.out.println( price.getVendorId() + " " +
		  price.getIsinNo() + " " + price.getPrice() + " " + price.getTimestamp()));*/
		 
        
        

    }
}
