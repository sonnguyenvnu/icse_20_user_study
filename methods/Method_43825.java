private Map<String,CoinMarketCapTicker> getNewTickers() throws IOException {
  Map<String,CoinMarketCapTicker> freshTickers=new HashMap<>();
  List<CoinMarketCapTicker> tt=getCoinMarketCapTickers();
  for (  CoinMarketCapTicker t : tt) {
    freshTickers.put(t.getSymbol(),t);
  }
  return freshTickers;
}
