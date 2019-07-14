private static void getMarketDataTooFastWithRetries(final MarketDataService service,final CurrencyPair cp) throws Exception {
  System.out.println("Polling for orderbooks too fast using Retries:");
  final Callable<OrderBook> orderBookAction=new Callable<OrderBook>(){
    @Override public OrderBook call() throws Exception {
      return service.getOrderBook(cp);
    }
  }
;
  for (int i=0; i < 50; i++) {
    OrderBook ob=Retries.callWithRetries(10,1,orderBookAction,TOO_FREQUENT_REQUESTS);
    System.out.println(ob);
  }
}
