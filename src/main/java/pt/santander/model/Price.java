package pt.santander.model;

import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class Price {

	@CsvBindByPosition(position = 0)
    private Long id;

    @CsvBindByPosition(position = 1)
    private String Name;

    @CsvBindByPosition(position = 2)
    private double bid;

    @CsvBindByPosition(position = 3)
    private double ask;

    @CsvBindByPosition(position = 4)
    @CsvDate("dd-MM-yyyy HH:mm:ss:SSS")
    private Date timestamp;

    
    
    
    
    
    //GETTERS AND SETTERS
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
    
    
}
