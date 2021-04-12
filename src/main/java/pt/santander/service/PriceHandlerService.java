package pt.santander.service;

import org.springframework.stereotype.Service;

import pt.santander.interfaces.PriceHandlerServiceInterface;
import pt.santander.model.Price;

@Service
public class PriceHandlerService implements PriceHandlerServiceInterface {

	/**
	 * Apply all current politics to Price
	 * 
	 * @param Price to be adjusted
	 * 
	 * @return All rules related to price applied
	 * */
	public Price applyPricePolitics(Price price) {
		price = adjustBidPrice(price);
		price = adjustAskPrice(price);
		
		return price;
	}
	
	/**
	 * Adjusts bid price
	 * 
	 * @param price object to be adjusted
	 * 
	 * @return price with bid adjusted
	 * */
	private static Price adjustBidPrice(Price price) {
		double valueSubtract = (price.getBid() * 0.001);
		price.setBid(price.getBid() - valueSubtract);
		
		return price;
	}
	
	/**
	 * Adjusts bid price
	 * 
	 * @param price object to be adjusted
	 * 
	 * @return price with ask adjusted
	 * */
	private static Price adjustAskPrice(Price price) {
		double valueAdd = (price.getAsk() * 0.001);
		price.setAsk(price.getAsk() + valueAdd);
		
		return price;
	}
}
