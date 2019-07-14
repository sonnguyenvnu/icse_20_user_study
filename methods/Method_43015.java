/** 
 * @param orders
 * @param orderType
 * @param currencyPair
 * @return
 */
private static List<LimitOrder> transformArrayToLimitOrders(BigDecimal[][] orders,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> limitOrders=new ArrayList<>();
  if (orders != null) {
    for (    BigDecimal[] order : orders) {
      limitOrders.add(new LimitOrder(orderType,order[1],currencyPair,null,new Date(),order[0]));
    }
  }
  return limitOrders;
}
