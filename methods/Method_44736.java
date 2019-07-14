public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.counter.getCurrencyCode().toUpperCase() + "_" + currencyPair.base.getCurrencyCode().toUpperCase();
}
