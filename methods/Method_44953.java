public static String adaptCcyPairsToUrlFormat(Iterable<CurrencyPair> currencyPairs){
  return StreamSupport.stream(currencyPairs.spliterator(),false).map(YoBitAdapters::adaptCcyPairToUrlFormat).collect(Collectors.joining("-"));
}
