public Map<CurrencyPair,Ticker> tickers() throws IOException {
  Map<String,Map<String,String>> tickers=exmo.ticker();
  Map<CurrencyPair,Ticker> results=new HashMap<>();
  for (  String market : tickers.keySet()) {
    CurrencyPair currencyPair=adaptMarket(market);
    Ticker ticker=ExmoAdapters.adaptTicker(currencyPair,tickers.get(market));
    results.put(ticker.getCurrencyPair(),ticker);
  }
  return results;
}
