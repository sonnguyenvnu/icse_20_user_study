private String getPairListAsString(Iterable<CurrencyPair> currencyPairs){
  String markets=YoBitAdapters.adaptCcyPairsToUrlFormat(currencyPairs);
  if (markets.length() > MAX_PAIR_LIST_SIZE) {
    throw new ExchangeException("URL too long: YoBit allows a maximum of " + MAX_PAIR_LIST_SIZE + " characters for total pair lists size. Provided string is " + markets.length() + " characters long.");
  }
  return markets;
}
