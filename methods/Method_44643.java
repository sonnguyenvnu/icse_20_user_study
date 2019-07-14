public static String adaptSymbol(CurrencyPair currencyPair){
  return (currencyPair.base.getCurrencyCode() + "_" + currencyPair.counter.getCurrencyCode()).toLowerCase();
}
