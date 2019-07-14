/** 
 * Liquidate long or short contract using a limit order 
 */
public String liquidateLimitOrder(LimitOrder limitOrder) throws IOException {
  long orderId=futuresTrade(OkCoinAdapters.adaptSymbol(limitOrder.getCurrencyPair()),limitOrder.getType() == OrderType.BID || limitOrder.getType() == OrderType.EXIT_BID ? "3" : "4",limitOrder.getLimitPrice().toPlainString(),limitOrder.getOriginalAmount().toPlainString(),futuresContract,0,leverRate).getOrderId();
  return String.valueOf(orderId);
}
