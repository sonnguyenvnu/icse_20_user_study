public static String currencyPairToBitMarketCurrencyPair(CurrencyPair currencyPair){
  if (currencyPair.base.getCurrencyCode().equals("LiteMineX") && currencyPair.counter.getCurrencyCode().equals("BTC")) {
    return "LiteMineXBTC";
  }
 else {
    return currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
  }
}
