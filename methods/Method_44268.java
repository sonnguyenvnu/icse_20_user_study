public static String toPairString(CurrencyPair currencyPair){
  return currencyPair.base.toString().toLowerCase() + currencyPair.counter.toString().toLowerCase();
}
