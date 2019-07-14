public static ExchangeMetaData adaptMetaData(BitcoinAverageTickers tickers,ExchangeMetaData bAMetaData){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=new HashMap<>();
  for (  String currency : tickers.getTickers().keySet()) {
    if (!currency.startsWith("BTC")) {
      throw new IllegalStateException("Unsupported currency: " + currency);
    }
    currencyPairs.put(new CurrencyPair(BTC,Currency.getInstance(currency.substring(3))),null);
  }
  return new ExchangeMetaData(currencyPairs,Collections.<Currency,CurrencyMetaData>emptyMap(),null,null,null);
}
