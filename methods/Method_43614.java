public ExchangeMetaData getMetadata() throws IOException {
  CobinhoodResponse<CobinhoodTradingPairs> response=getCobinhoodTradingPairs();
  List<CobinhoodCurrencyPair> pairs=response.getResult().getPairs();
  return CobinhoodAdapters.adaptMetadata(pairs);
}
