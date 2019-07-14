public static Ticker convert(OkexTokenPairInformation i){
  return new Ticker.Builder().currencyPair(toPair(i.getInstrumentId())).last(i.getLast()).bid(i.getBid()).ask(i.getAsk()).volume(i.getBaseVolume24h()).quoteVolume(i.getQuoteVolume24h()).timestamp(i.getTimestamp()).build();
}
