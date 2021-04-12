package pt.santander.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import pt.santander.interfaces.PriceRepositoryInterface;
import pt.santander.model.Price;

@Repository
public class PriceRepository implements PriceRepositoryInterface{
	
	//I have no database to store prices, so I will simulate it with this List containing all prices
	private static List<Price> allPrices = new ArrayList<Price>();
	
	/**
	 * get all prices from all currencies
	 * 
	 * */
	public List<Price> findAllPrices() {
		return allPrices;
	}
	
	/**
	 * get latest price from all currencies
	 * 
	 * */
	public Price findLatestPrice() {
		
		return allPrices.size() > 0 ?
				allPrices.get(allPrices.size() - 1)
				: null;
	}
	
	/**
	 * get specific price by id
	 * 
	 * @param id searched
	 * 
	 * @return Price with correspondent id
	 * @throws @{@link NoSuchElementException} when id is not found
	 * */
	public Price findbyId(Long id) {
		return allPrices.stream().filter(price -> price.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException());
	}
	
	/**
	 * get specific price by currency
	 * 
	 * @param currency searched
	 * 
	 * @return Price with correspondent name
	 * 
	 * */
	public Price findLowestPriceByName(String name) {
		List<Price> prices = allPrices.stream().filter(price -> price.getName().equals(name)).collect(Collectors.toList());
		
		return prices.stream().min(Comparator.comparing(Price::getBid)).get();
	
	}
	
	/**
	 * get all prices of specific currency
	 * 
	 * @param currency searched
	 * 
	 * @return List of prices of specific currency
	 * 
	 * */
	public List<Price> findAllPricesByName(String name) {
		
		return allPrices.stream().filter(price -> price.getName().equals(name)).collect(Collectors.toList());

	}
}
