package com.matsuri.TradePrice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value="com.matsuri")
@SpringBootApplication
public class TradePriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradePriceApplication.class, args);
		
	}

}
