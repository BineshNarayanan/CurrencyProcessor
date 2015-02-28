package com.processor.message.store;

/**
 * The old Plain Old Java Object Message.
 * @author BNaraya
 *
 */
public class Message {
	
	public static final String JSONFIELD_MESSAGE_ID = "id";
	public static final String JSONFIELD_USER_ID = "userId";
	public static final String JSONFIELD_AMOUNT_BUY = "amountBuy";
	public static final String JSONFIELD_AMOUNT_SELL ="amountSell";
	public static final String JSONFIELD_RATE = "rate";
	public static final String JSONFIELD_CURRENCY_FROM = "currencyFrom";
	public static final String JSONFIELD_CURRENCY_TO = "currencyTo";
	public static final String JSONFIELD_ORIGINATING_COUNTRY = "originatingCountry";
	public static final String JSONFIELD_TIME = "timePlaced";
	
	private long id;
	
	protected Message(long id){
		this.id = id;
		//id = id + 1;
	}
	
	private long userId;
	private String currencyFrom;
	private String currencyTo;
	private double amountSell;
	private double amountBuy;
	private double rate;
	private String timePlaced;
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
