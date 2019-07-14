/** 
 * Bitfinex API does not provide filtering option. So we should filter orders ourselves 
 */
@SuppressWarnings("unchecked") private OpenOrders filterOrders(OpenOrders rawOpenOrders,OpenOrdersParams params){
  if (params == null) {
    return rawOpenOrders;
  }
  List<LimitOrder> openOrdersList=rawOpenOrders.getOpenOrders();
  openOrdersList.removeIf(openOrder -> !params.accept(openOrder));
  return new OpenOrders(openOrdersList,(List<Order>)rawOpenOrders.getHiddenOrders());
}
