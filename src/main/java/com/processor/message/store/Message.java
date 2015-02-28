package com.processor.message.store;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("message")
public class Message {
	
	private long id;
	
	protected Message(long id){
		this.id = id;
		//id = id + 1;
	}
	
	@XStreamAlias("usedId")
	private long userId;
	
	@XStreamAlias("currencyFrom")
	private String currencyFrom;
	
	@XStreamAlias("currencyTo")
	private String currencyTo;
	
	@XStreamAlias("amountSell")
	private double amountSell;
	
	@XStreamAlias("amountBuy")
	private double amountBuy;
	
	@XStreamAlias("rate")
	private double rate;
	
	@XStreamAlias("timePlaced")
	private String timePlaced;
	
	@XStreamAlias("originatingCountry")
	private String originatingCountry;
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}
	public void setAmountSell(double amountSell) {
		this.amountSell = amountSell;
	}
	public void setAmountBuy(double amountBuy) {
		this.amountBuy = amountBuy;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public void setTimePlaced(String timePlaced) {
		this.timePlaced = timePlaced;
	}
	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}
	
	public long getId() {
		return id;
	}
	public long getUserId() {
		return userId;
	}
	public String getCurrencyFrom() {
		return currencyFrom;
	}
	public String getCurrencyTo() {
		return currencyTo;
	}
	public double getAmountSell() {
		return amountSell;
	}
	public double getAmountBuy() {
		return amountBuy;
	}
	public double getRate() {
		return rate;
	}
	public String getTimePlaced() {
		return timePlaced;
	}
	public String getOriginatingCountry() {
		return originatingCountry;
	}
}
