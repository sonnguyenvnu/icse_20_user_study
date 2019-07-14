/** 
 * Liquidate long or short contract (depending on market order order type) using a market order
 */
public String liquidateMarketOrder(MarketOrder marketOrder) throws IOException {
  long orderId=futuresTrade(OkCoinAdapters.adaptSymbol(marketOrder.getCurrencyPair()),marketOrder.getType() == OrderType.BID || marketOrder.getType() == OrderType.EXIT_BID ? "3" : "4","0",marketOrder.getOriginalAmount().toPlainString(),futuresContract,1,leverRate).getOrderId();
  return String.valueOf(orderId);
}
