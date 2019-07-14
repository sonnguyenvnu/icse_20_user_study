private static void generic(MarketDataService marketDataService) throws IOException {
  System.out.println("----------GENERIC---------");
  System.out.println("Market data for " + PAIR + ":");
  Ticker ticker=marketDataService.getTicker(PAIR);
  System.out.println(ticker);
  OrderBook orderBook=marketDataService.getOrderBook(PAIR);
  System.out.println(orderBook);
}
