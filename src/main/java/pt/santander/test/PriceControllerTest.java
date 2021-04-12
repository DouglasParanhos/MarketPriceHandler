package pt.santander.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pt.santander.PriceReader;
import pt.santander.controller.PriceController;
import pt.santander.model.Price;
import pt.santander.repository.PriceRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PriceControllerTest {
	 	
		@Autowired
		private WebApplicationContext webApplicationContext;
	
		@Autowired
	    private MockMvc mvc;

	    @Autowired
	    private PriceRepository repository;
	    
	    @Autowired
	    private PriceController controller;
	    
	    @Autowired
	    private PriceReader priceReader;
	    
	    @Before
	    public void setUp() {
	      System.out.println("Starting PriceController tests");
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	      
	      repository.findAllPrices().clear();
	      
	      Price price = new Price();
	    	price.setId(1L);
	    	price.setBid(0.99);
	    	price.setAsk(1.5);
	    	price.setName("EUR/JPY");
	      
	      repository.findAllPrices().add(price);
	    }
	    
	    @Test
	    public void shouldCheckLatestPriceAndReturn200() throws Exception{
		    this.mvc.perform(get("/latest-price")
		        	.accept(MediaType.APPLICATION_JSON))
		    		.andExpect(status().isOk())
		    		.andExpect(jsonPath("$.id", is(notNullValue())))
		    		.andExpect(jsonPath("$.bid", is(0.99)))
		    		.andExpect(jsonPath("$.ask", is(1.5)))
		    		.andExpect(jsonPath("$.name", is("EUR/JPY")));
	    }

	    @Test
	    public void shouldCheckAllPricesAndReturn200() throws Exception{
		    this.mvc.perform(get("/all-prices")
		        	.accept(MediaType.APPLICATION_JSON))
		    		.andExpect(status().isOk())
		    		.andExpect(jsonPath("$[0].id", is(notNullValue())))
		    		.andExpect(jsonPath("$[0].bid", is(0.99)))
		    		.andExpect(jsonPath("$[0].ask", is(1.5)))
		    		.andExpect(jsonPath("$[0].name", is("EUR/JPY")));
	    }
	    
	    @Test
	    public void shouldCheckPriceAndReturn200() throws Exception{
	    	
		    this.mvc.perform(get("/price/1")
		        	.accept(MediaType.APPLICATION_JSON))
		    		.andExpect(status().isOk())
		    		.andExpect(jsonPath("$.id", is(notNullValue())))
		    		.andExpect(jsonPath("$.bid", is(0.99)))
		    		.andExpect(jsonPath("$.ask", is(1.5)))
		    		.andExpect(jsonPath("$.name", is("EUR/JPY")));
	    }
	    
	    @Test
	    public void shouldCheckLowestPriceByCurrencyAndReturn200() throws Exception{
		    this.mvc.perform(get("/lowest-price/EUR-JPY")
		        	.accept(MediaType.APPLICATION_JSON))
		    		.andExpect(status().isOk())
		    		.andExpect(jsonPath("$.id", is(notNullValue())))
		    		.andExpect(jsonPath("$.bid", is(0.99)))
		    		.andExpect(jsonPath("$.ask", is(1.5)))
		    		.andExpect(jsonPath("$.name", is("EUR/JPY")));
	    }
	    
	    @Test
	    public void shouldCheckAllPricesByCurrencyAndReturn200() throws Exception{
		    this.mvc.perform(get("/all-prices/EUR-JPY")
		        	.accept(MediaType.APPLICATION_JSON))
		    		.andExpect(status().isOk())
		    		.andExpect(jsonPath("$[0].id", is(notNullValue())))
		    		.andExpect(jsonPath("$[0].bid", is(0.99)))
		    		.andExpect(jsonPath("$[0].ask", is(1.5)))
		    		.andExpect(jsonPath("$[0].name", is("EUR/JPY")));
	    }
}
