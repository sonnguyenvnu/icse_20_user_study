public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.counter.getCurrencyCode().toUpperCase() + "-" + currencyPair.base.getCurrencyCode().toUpperCase();
}
