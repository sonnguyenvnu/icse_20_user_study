private static Ticker getTickerOf(BaseKoineksTicker koineksTicker,Currency currency){
  if (koineksTicker != null) {
    BigDecimal last=koineksTicker.getCurrent();
    BigDecimal lowestAsk=koineksTicker.getAsk();
    BigDecimal highestBid=koineksTicker.getBid();
    BigDecimal volume=koineksTicker.getVolume();
    BigDecimal high24hr=koineksTicker.getHigh();
    BigDecimal low24hr=koineksTicker.getLow();
    String timestampStr=koineksTicker.getTimestamp();
    Date timestamp=new Date(Long.valueOf(timestampStr));
    return new Ticker.Builder().currencyPair(new CurrencyPair(currency,Currency.TRY)).last(last).bid(highestBid).ask(lowestAsk).high(high24hr).low(low24hr).timestamp(timestamp).volume(volume).build();
  }
  return null;
}
