public static void raw(BinanceExchange exchange,BinanceMarketDataService marketDataService) throws IOException {
  List<BinanceTicker24h> tickers=new ArrayList<>();
  for (  CurrencyPair cp : exchange.getExchangeMetaData().getCurrencyPairs().keySet()) {
    if (cp.counter == Currency.USDT) {
      tickers.add(marketDataService.ticker24h(cp));
    }
  }
  Collections.sort(tickers,new Comparator<BinanceTicker24h>(){
    @Override public int compare(    BinanceTicker24h t1,    BinanceTicker24h t2){
      return t2.getPriceChangePercent().compareTo(t1.getPriceChangePercent());
    }
  }
);
  tickers.stream().forEach(t -> {
    System.out.println(t.getCurrencyPair() + " => " + String.format("%+.2f%%",t.getPriceChangePercent()));
  }
);
  System.out.println("raw out end");
}
