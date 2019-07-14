public static String adaptCurrencyPair(CurrencyPair pair){
  return pair == null ? null : pair.base.getCurrencyCode() + "-" + pair.counter.getCurrencyCode();
}
