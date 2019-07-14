public static Ticker.Builder adaptTickerFull(CurrencyPair pair,SymbolTickResponse stats){
  return new Ticker.Builder().currencyPair(pair).bid(stats.getBuy()).ask(stats.getSell()).last(stats.getLast()).high(stats.getHigh()).low(stats.getLow()).volume(stats.getVol()).quoteVolume(stats.getVolValue()).open(stats.getOpen()).timestamp(new Date(stats.getTime()));
}
