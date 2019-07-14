public static String toBiboxPair(CurrencyPair pair){
  return pair.base.getCurrencyCode() + "_" + pair.counter.getCurrencyCode();
}
