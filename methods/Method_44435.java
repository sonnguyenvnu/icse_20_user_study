public static List<Ticker> adaptTickers(Map<String,KrakenTicker> krackenTickers){
  List<Ticker> tickers=new ArrayList<>();
  for (  Map.Entry<String,KrakenTicker> ticker : krackenTickers.entrySet()) {
    CurrencyPair pair=KrakenUtils.translateKrakenCurrencyPair(ticker.getKey());
    tickers.add(adaptTicker(ticker.getValue(),pair));
  }
  return tickers;
}
