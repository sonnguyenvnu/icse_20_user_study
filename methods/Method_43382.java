public static String toPairString(CurrencyPair currency){
  return String.format("%s_%s",currency.base.getCurrencyCode().toLowerCase(),currency.counter.getCurrencyCode().toLowerCase());
}
