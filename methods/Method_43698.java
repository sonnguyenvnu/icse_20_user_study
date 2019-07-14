/** 
 * Creates a 'stop' order. Stop limit order converts to a limit order when the stop amount is triggered. The limit order can have a different price than the stop price. <p>If the stop order has no limit price it will execute as a market order once the stop price is broken
 * @param stopOrder
 * @return
 */
public static CoinbaseProPlaceOrder adaptCoinbaseProStopOrder(StopOrder stopOrder){
  if (stopOrder.getLimitPrice() == null) {
    return new CoinbaseProPlaceMarketOrder.Builder().productId(adaptProductID(stopOrder.getCurrencyPair())).type(CoinbaseProPlaceOrder.Type.market).side(adaptSide(stopOrder.getType())).size(stopOrder.getOriginalAmount()).stop(adaptStop(stopOrder.getType())).stopPrice(stopOrder.getStopPrice()).build();
  }
  return new CoinbaseProPlaceLimitOrder.Builder().productId(adaptProductID(stopOrder.getCurrencyPair())).type(CoinbaseProPlaceOrder.Type.limit).side(adaptSide(stopOrder.getType())).size(stopOrder.getOriginalAmount()).stop(adaptStop(stopOrder.getType())).stopPrice(stopOrder.getStopPrice()).price(stopOrder.getLimitPrice()).build();
}
