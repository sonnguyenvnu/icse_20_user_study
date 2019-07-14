public static String format(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode() + "_" + currencyPair.counter.getCurrencyCode();
}
