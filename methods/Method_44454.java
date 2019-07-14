protected String delimitAssetPairs(CurrencyPair[] currencyPairs) throws IOException {
  return currencyPairs != null && currencyPairs.length > 0 ? Arrays.stream(currencyPairs).map(KrakenUtils::createKrakenCurrencyPair).filter(Objects::nonNull).collect(Collectors.joining(",")) : null;
}
