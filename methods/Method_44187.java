private static void generic(MarketDataService marketDataService) throws IOException {
  System.out.println("----------GENERIC---------");
  OrderBook orderBook=marketDataService.getOrderBook(PAIR);
  System.out.println(orderBook);
}
