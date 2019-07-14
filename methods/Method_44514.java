public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode().toLowerCase() + currencyPair.counter.getCurrencyCode().toLowerCase();
}
