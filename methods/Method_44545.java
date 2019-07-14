public static OpenOrders adaptActiveOrders(final Map<Long,LiquiOrderInfo> orders){
  final List<LimitOrder> openOrders=new ArrayList<>();
  for (  final Map.Entry<Long,LiquiOrderInfo> entry : orders.entrySet()) {
    openOrders.add(adaptActiveOrder(entry.getValue(),entry.getKey()));
  }
  return new OpenOrders(openOrders);
}
