public static CurrencyPair bitMarketCurrencyPairToCurrencyPair(String currencyPair){
  if (currencyPair.equals("LiteMineXBTC")) {
    return new CurrencyPair("LiteMineX","BTC");
  }
 else   if (currencyPair.length() == 6) {
    String ccyA=currencyPair.substring(0,3);
    String ccyB=currencyPair.substring(3);
    return new CurrencyPair(Currency.getInstance(ccyA),Currency.getInstance(ccyB));
  }
 else {
    throw new IllegalStateException("Cannot convert '" + currencyPair + "' into a CurrencyPair");
  }
}
