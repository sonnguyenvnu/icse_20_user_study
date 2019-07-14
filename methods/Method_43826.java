public static Ticker adaptTicker(CmcTicker ticker,CurrencyPair currencyPair){
  Date timestamp=ticker.getLastUpdated();
  CmcQuote cmcQuote=ticker.getQuote().get(currencyPair.counter.getCurrencyCode());
  BigDecimal price=cmcQuote.getPrice();
  BigDecimal volume24h=cmcQuote.getVolume24h();
  return new Ticker.Builder().currencyPair(currencyPair).timestamp(timestamp).open(price).last(price).bid(price).ask(price).high(price).low(price).vwap(price).volume(volume24h).build();
}
