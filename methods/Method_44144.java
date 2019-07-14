public static void rawAll(BinanceExchange exchange,BinanceMarketDataService marketDataService) throws IOException {
  List<BinanceTicker24h> tickers=new ArrayList<>();
  tickers.addAll(marketDataService.ticker24h());
  Collections.sort(tickers,new Comparator<BinanceTicker24h>(){
    @Override public int compare(    BinanceTicker24h t1,    BinanceTicker24h t2){
      return t2.getPriceChangePercent().compareTo(t1.getPriceChangePercent());
    }
  }
);
  tickers.stream().forEach(t -> {
    System.out.println(t.getSymbol() + " => " + String.format("%+.2f%%",t.getLastPrice()));
  }
);
}
