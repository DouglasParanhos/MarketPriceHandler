package pt.santander.utils;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import pt.santander.model.Price;

public class CsvHandler {

	/**
	 * Convert a String to a single Price
	 * 
	 * @param String csv containing the price
	 * 
	 * @return Price object converted
	 * 
	 * */
	public static Price convertCsvToPrice(String csv) throws IllegalStateException, FileNotFoundException{
		List<Price> prices = new CsvToBeanBuilder(new StringReader(csv)).withType(Price.class).build().parse();
		
		return prices.get(0);
	}
	
	/**
	 * Convert a String to a list of Prices
	 * 
	 * @param String csv containing the prices
	 * 
	 * @return List of Prices converted
	 * 
	 * */
	public static List<Price> convertCsvToPrices(String csv) throws IllegalStateException, FileNotFoundException {
		List<Price> prices = new CsvToBeanBuilder(new StringReader(csv)).withType(Price.class).build().parse();
		
		return prices;
	}
}
