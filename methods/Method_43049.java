/** 
 * Create an individual order. 
 */
public static LimitOrder createOrder(CurrencyPair currencyPair,BitcoindeOrder bitcoindeOrder,Order.OrderType orderType,String orderId,Date timeStamp){
  return new LimitOrder(orderType,bitcoindeOrder.getAmount(),currencyPair,orderId,timeStamp,bitcoindeOrder.getPrice());
}
