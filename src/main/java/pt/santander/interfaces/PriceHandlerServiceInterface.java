package pt.santander.interfaces;

import pt.santander.model.Price;

public interface PriceHandlerServiceInterface {

	public Price applyPricePolitics(Price price);
}
