package pt.santander;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.santander.model.Price;
import pt.santander.repository.PriceRepository;
import pt.santander.service.PriceHandlerService;
import pt.santander.utils.CsvHandler;

@Component
public class PriceReader {
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private PriceHandlerService priceHandlerService;
	
	/**
	 * Consume from publish prices bids
	 * 
	 * @param String message containing the CSV with price(s)
	 * 
	 * */
	public void onMessage(String message) {
		
		try {
			Price price = CsvHandler.convertCsvToPrice(message);//assuming there is only one
			price = priceHandlerService.applyPricePolitics(price);
			
			publishPrice(price);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block; do something when there are errors
			e.printStackTrace();
		}
	}
	
	/**
	 * Publish price when it is ready for users
	 * Here i have no database or anything, so my "publish" will be add to the list with all currencies
	 * 
	 * @param Price to be published
	 * 
	 * */
	private void publishPrice(Price price) {
		priceRepository.findAllPrices().add(price);
	}
	
}
