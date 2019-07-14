public static List<Ticker> adaptTickers(BitZTickerAllResult bitZTickerAllResult){
  List<Ticker> tickers=new ArrayList<Ticker>();
  for (  Entry<String,BitZTicker> ticker : bitZTickerAllResult.getData().getAllTickers().entrySet()) {
    CurrencyPair pair=BitZUtils.toCurrencyPair(ticker.getKey());
    if (pair != null) {
      tickers.add(adaptTicker(ticker.getValue(),pair));
    }
  }
  return tickers;
}
