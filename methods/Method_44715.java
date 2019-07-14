/** 
 * @param PaymiumMarketOrders
 * @param orderType
 * @param currencyPair
 */
private static List<LimitOrder> adaptMarketOrderToLimitOrder(List<PaymiumMarketOrder> PaymiumMarketOrders,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> orders=new ArrayList<>(PaymiumMarketOrders.size());
  for (  PaymiumMarketOrder PaymiumMarketOrder : PaymiumMarketOrders) {
    LimitOrder limitOrder=new LimitOrder(orderType,PaymiumMarketOrder.getAmount(),currencyPair,null,new Date(PaymiumMarketOrder.getTimestamp()),PaymiumMarketOrder.getPrice());
    orders.add(limitOrder);
  }
  return orders;
}
