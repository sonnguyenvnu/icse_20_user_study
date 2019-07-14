/** 
 * Adapts a BitcoiniumOrderbook to a OrderBook Object
 * @param bitcoiniumOrderbook
 * @return the XChange OrderBook
 */
public static OrderBook adaptOrderbook(BitcoiniumOrderbook bitcoiniumOrderbook,CurrencyPair currencyPair){
  List<LimitOrder> asks=createOrders(currencyPair,Order.OrderType.ASK,bitcoiniumOrderbook.getAsks());
  List<LimitOrder> bids=createOrders(currencyPair,Order.OrderType.BID,bitcoiniumOrderbook.getBids());
  Date date=new Date(bitcoiniumOrderbook.getBitcoiniumTicker().getTimestamp());
  return new OrderBook(date,asks,bids);
}
