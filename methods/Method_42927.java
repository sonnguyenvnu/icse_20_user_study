public static List<OrderBook> adaptAllOrderBooks(List<BiboxOrderBook> biboxOrderBooks){
  return biboxOrderBooks.stream().map(ob -> BiboxAdapters.adaptOrderBook(ob,adaptCurrencyPair(ob.getPair()))).collect(Collectors.toList());
}
