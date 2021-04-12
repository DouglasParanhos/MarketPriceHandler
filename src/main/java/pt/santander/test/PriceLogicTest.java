package pt.santander.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import pt.santander.PriceReader;
import pt.santander.controller.PriceController;
import pt.santander.repository.PriceRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PriceLogicTest {

	    @Autowired
	    private PriceRepository repository;
	    
	    @Autowired
	    private PriceReader priceReader;
	    
	    @Before
	    public void setUp() {
	    	System.out.println("Starting PriceLogic tests");
	    }

	    @Test
	    public void checkPriceWasPublishedCorrectly() {
	    	String entrada1 = "1, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
	    	String entrada2 = "2, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:001";
	    	String entrada3 = "3, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:001";
	    	String entrada4 = "4, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:100";
	    	
	    	priceReader.onMessage(entrada1);
	    	assertEquals(1, repository.findAllPrices().size());
	    	
	    	priceReader.onMessage(entrada2);
	    	assertEquals(2, repository.findAllPrices().size());
	    	
	    	priceReader.onMessage(entrada3);
	    	assertEquals(3, repository.findAllPrices().size());
	    	
	    	priceReader.onMessage(entrada4);
	    	assertEquals(4, repository.findAllPrices().size());
	    }
	    
	    @Test
	    public void checkPriceWasAdjustedCorrectly() {
	    	String entrada1 = "1, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
	    	repository.findAllPrices().clear();
	    	
	    	priceReader.onMessage(entrada1);
	    	
	    	assertEquals(1.0989, repository.findAllPrices().get(0).getBid());
	    	assertEquals(1.2012, repository.findAllPrices().get(0).getAsk());
	    }
	    
	    
}
