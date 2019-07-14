public static String adaptCurrencyPairsToTickersParam(Collection<CurrencyPair> currencyPairs){
  return currencyPairs.stream().map(currencyPair -> "t" + currencyPair.base + currencyPair.counter).collect(Collectors.joining(","));
}
