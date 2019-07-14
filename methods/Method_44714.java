/** 
 * @param marketDepth
 * @param currencyPair
 * @return new order book
 */
public static OrderBook adaptMarketDepth(PaymiumMarketDepth marketDepth,CurrencyPair currencyPair){
  List<LimitOrder> asks=adaptMarketOrderToLimitOrder(marketDepth.getAsks(),OrderType.ASK,currencyPair);
  List<LimitOrder> bids=adaptMarketOrderToLimitOrder(marketDepth.getBids(),OrderType.BID,currencyPair);
  Collections.reverse(bids);
  return new OrderBook(null,asks,bids);
}
