public static String toMarket(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
}
