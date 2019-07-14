public static String adaptProductID(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode() + "-" + currencyPair.counter.getCurrencyCode();
}
