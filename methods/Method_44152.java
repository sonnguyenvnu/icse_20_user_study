private static void printOpenOrders(TradeService tradeService,OpenOrdersParams openOrdersParams) throws IOException {
  OpenOrders openOrders=tradeService.getOpenOrders(openOrdersParams);
  System.out.printf("Open Orders for %s: %s%n",openOrdersParams,openOrders);
}
