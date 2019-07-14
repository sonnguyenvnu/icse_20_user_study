public Map<CurrencyPair,LivecoinOrderBook> getAllOrderBooksRaw(int depth,Boolean groupByPrice) throws IOException {
  return LivecoinAdapters.adaptToCurrencyPairKeysMap(service.getAllOrderBooks(depth,groupByPrice.toString()));
}
