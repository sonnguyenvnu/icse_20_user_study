public static CurrencyPair convert(String symbol){
  for (  Currency base : Arrays.asList(Currency.BTC,Currency.ETH,Currency.BNB,Currency.USDT)) {
    if (symbol.contains(base.toString())) {
      String counter=symbol.replace(base.toString(),"");
      return new CurrencyPair(base,new Currency(counter));
    }
  }
  throw new IllegalArgumentException("Could not parse currency pair from '" + symbol + "'");
}
