protected Ticker mapKunaTicker2Ticker(KunaTimeTicker kunaTimeTicker,CurrencyPair currencyPair){
  KunaTicker kunaTicker=kunaTimeTicker.getTicker();
  Date timestamp=new Date(kunaTimeTicker.getTimestamp());
  Ticker.Builder builder=new Ticker.Builder().currencyPair(currencyPair).timestamp(timestamp).ask(kunaTicker.getBuy()).bid(kunaTicker.getSell()).high(kunaTicker.getHigh()).low(kunaTicker.getLow()).last(kunaTicker.getLast()).volume(kunaTicker.getVol());
  return builder.build();
}
