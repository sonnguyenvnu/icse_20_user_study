public static String currencyPairToBook(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode().toLowerCase() + "_" + currencyPair.counter.getCurrencyCode().toLowerCase();
}
