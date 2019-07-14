private static void generic(Exchange exchange) throws IOException {
  TradeService tradeService=exchange.getTradeService();
  printOpenOrders(tradeService);
  LimitOrder limitOrder=new LimitOrder(Order.OrderType.BID,new BigDecimal("0.01"),CurrencyPair.BTC_USD,"",new Date(),new BigDecimal("900"));
  String limitOrderReturnValue=null;
  try {
    limitOrderReturnValue=tradeService.placeLimitOrder(limitOrder);
    System.out.println("Limit Order return value: " + limitOrderReturnValue);
    printOpenOrders(tradeService);
    boolean cancelResult=tradeService.cancelOrder(limitOrderReturnValue);
    System.out.println("Canceling returned " + cancelResult);
  }
 catch (  ExchangeException e) {
    System.out.println(e.getMessage());
  }
  printOpenOrders(tradeService);
}
