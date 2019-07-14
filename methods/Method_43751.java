public static String toSymbol(CurrencyPair pair){
  return pair.base.getCurrencyCode() + "-" + pair.counter.getCurrencyCode();
}
