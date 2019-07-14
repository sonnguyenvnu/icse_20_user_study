public synchronized Ticker toTicker(){
  CurrencyPair currencyPair=pair;
  if (currencyPair == null) {
    currencyPair=BinanceAdapters.adaptSymbol(symbol);
  }
  if (ticker == null) {
    ticker=new Ticker.Builder().currencyPair(currencyPair).open(openPrice).ask(askPrice).bid(bidPrice).last(lastPrice).high(highPrice).low(lowPrice).volume(volume).vwap(weightedAvgPrice).askSize(askQty).bidSize(bidQty).quoteVolume(quoteVolume).build();
  }
  return ticker;
}
