public static String adaptCurrencyPairToSymbol(CurrencyPair currencyPair){
  return currencyPair == null ? null : currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
}
