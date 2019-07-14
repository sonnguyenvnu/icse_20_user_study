public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode().toUpperCase() + "_" + currencyPair.counter.getCurrencyCode().toUpperCase();
}
