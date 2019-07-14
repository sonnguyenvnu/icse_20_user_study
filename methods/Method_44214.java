public static List<Ticker> convertTickerMap(Map<String,EXXTicker> exxTickers){
  List<Ticker> tickers=new ArrayList<Ticker>();
  for (  Map.Entry<String,EXXTicker> exxTickerMap : exxTickers.entrySet()) {
    String pair=exxTickerMap.getKey();
    if (pair != null) {
      tickers.add(new Ticker.Builder().ask(exxTickerMap.getValue().getSell()).bid(exxTickerMap.getValue().getBuy()).high(exxTickerMap.getValue().getHigh()).low(exxTickerMap.getValue().getLow()).volume(exxTickerMap.getValue().getVol()).last(exxTickerMap.getValue().getLast()).timestamp(null).currencyPair(convertTradingPair(pair)).build());
    }
  }
  return tickers;
}
