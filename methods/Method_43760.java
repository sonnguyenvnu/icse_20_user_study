@Override public List<Ticker> getTickers(Params params) throws IOException {
  List<CoindirectMarket> coindirectMarkets=getCoindirectMarkets(1000);
  List<Ticker> tickerList=new ArrayList<>();
  for (int i=0; i < coindirectMarkets.size(); i++) {
    tickerList.add(getTicker(CoindirectAdapters.toCurrencyPair(coindirectMarkets.get(i).symbol)));
  }
  return tickerList;
}
