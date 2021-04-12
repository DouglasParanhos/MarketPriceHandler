package pt.santander.interfaces;

import java.util.List;

import pt.santander.model.Price;

public interface PriceRepositoryInterface {
	
		public List<Price> findAllPrices();
		
		public Price findLatestPrice();
		
		public Price findbyId(Long id);
		
		public Price findLowestPriceByName(String name);
		
		public List<Price> findAllPricesByName(String name);

}
