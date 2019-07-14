public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.counter.getCurrencyCode().toLowerCase() + "-" + currencyPair.base.getCurrencyCode().toLowerCase();
}
