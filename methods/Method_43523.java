/** 
 * CampBXOrderBook to a OrderBook Object
 * @param orderBook
 * @param currencyPair (e.g. BTC/USD)
 * @return
 */
public static OrderBook adaptOrders(CampBXOrderBook orderBook,CurrencyPair currencyPair){
  List<LimitOrder> asks=createOrders(currencyPair,Order.OrderType.ASK,orderBook.getAsks());
  List<LimitOrder> bids=createOrders(currencyPair,Order.OrderType.BID,orderBook.getBids());
  return new OrderBook(null,asks,bids);
}
