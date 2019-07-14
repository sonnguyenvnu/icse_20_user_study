public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
}
