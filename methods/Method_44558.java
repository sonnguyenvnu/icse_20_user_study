public static Map<CurrencyPair,LivecoinOrderBook> adaptToCurrencyPairKeysMap(LivecoinAllOrderBooks orderBooksRaw){
  Set<Map.Entry<String,LivecoinOrderBook>> entries=orderBooksRaw.getOrderBooksByPair().entrySet();
  Map<CurrencyPair,LivecoinOrderBook> converted=new HashMap<>(entries.size());
  for (  Map.Entry<String,LivecoinOrderBook> entry : entries) {
    String[] currencyPairSplit=entry.getKey().split("/");
    CurrencyPair currencyPair=new CurrencyPair(currencyPairSplit[0],currencyPairSplit[1]);
    converted.put(currencyPair,entry.getValue());
  }
  return converted;
}
