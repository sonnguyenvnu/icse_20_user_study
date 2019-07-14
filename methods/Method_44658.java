public static String toInstrument(CurrencyPair pair){
  return pair == null ? null : pair.base.getCurrencyCode() + "-" + pair.counter.getCurrencyCode();
}
