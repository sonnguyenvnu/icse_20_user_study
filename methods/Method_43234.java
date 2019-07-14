private Integer getPriceScale(List<BitmexTicker> tickers,CurrencyPair cp){
  return tickers.stream().filter(ticker -> ticker.getSymbol().equals(BitmexAdapters.adaptCurrencyPairToSymbol(cp))).findFirst().map(ticker -> ticker.getLastPrice().scale()).get();
}
