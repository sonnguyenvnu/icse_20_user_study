public Iterable<OrderBook> getOrderBooks(OrderBooksRequestParam params) throws IOException {
  if (params instanceof MultiCurrencyOrderBooksRequestParams) {
    MultiCurrencyOrderBooksRequestParams booksRequestParam=(MultiCurrencyOrderBooksRequestParams)params;
    List<OrderBook> res=new ArrayList<>();
    YoBitOrderBooksReturn orderBooks=getOrderBooks(booksRequestParam.currencyPairs,booksRequestParam.desiredDepth);
    for (    String ccyPair : orderBooks.orderBooks.keySet()) {
      CurrencyPair currencyPair=YoBitAdapters.adaptCurrencyPair(ccyPair);
      OrderBook orderBook=YoBitAdapters.adaptOrderBook(orderBooks.orderBooks.get(ccyPair),currencyPair);
      res.add(orderBook);
    }
    return res;
  }
  throw new IllegalStateException("Don't understand " + params);
}
