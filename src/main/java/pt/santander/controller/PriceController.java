package pt.santander.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pt.santander.model.Price;
import pt.santander.repository.PriceRepository;

@RestController
public class PriceController {
	
	@Autowired
	PriceRepository priceRepository;
	
	//get latest price from all currencies
	@GetMapping("/latest-price")
	@ResponseBody
	private Price findLatestPrice() {
		return priceRepository.findLatestPrice();
	}
	
	//get all prices from all currencies
	@GetMapping("/all-prices")
	@ResponseBody
	private List<Price> findAllPrices() {
		return priceRepository.findAllPrices();
	}
	
	//get price by id
	@GetMapping("/price/{id}")
	@ResponseBody
	private Price findbyId(@PathVariable Long id) {
		return priceRepository.findbyId(id);
	}
	
	//get lowest price of specific currency
	@GetMapping("/lowest-price/{name}")
	@ResponseBody
	private Price findLowestPriceByName(@PathVariable String name) {
		name = name.replace("-", "/");
		
		return priceRepository.findLowestPriceByName(name);
	}
	
	//get all prices of specific currency
	@GetMapping("/all-prices/{name}")
	@ResponseBody
	private List<Price> findAllPricesByName(@PathVariable String name) {
		name = name.replace("-", "/");
		
		return priceRepository.findAllPricesByName(name);
	}
	
}
