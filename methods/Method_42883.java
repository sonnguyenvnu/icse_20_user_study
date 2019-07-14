public static String getAcxMarket(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode().toLowerCase() + currencyPair.counter.getCurrencyCode().toLowerCase();
}
