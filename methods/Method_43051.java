public static String createBitcoindePair(CurrencyPair currencyPair){
  return currencyPair.base.getCurrencyCode().toLowerCase() + currencyPair.counter.getCurrencyCode().toLowerCase();
}
