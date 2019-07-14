public static LimitOrder adaptOrder(BittrexOrderBase order,OrderStatus status){
  OrderType type=order.getOrderType().equalsIgnoreCase("LIMIT_SELL") ? OrderType.ASK : OrderType.BID;
  String[] currencies=order.getExchange().split("-");
  CurrencyPair pair=new CurrencyPair(currencies[1],currencies[0]);
  return new LimitOrder.Builder(type,pair).originalAmount(order.getQuantity()).id(order.getOrderUuid()).timestamp(order.getOpened()).limitPrice(order.getLimit()).averagePrice(order.getPricePerUnit()).cumulativeAmount(order.getQuantityRemaining() == null ? null : order.getQuantity().subtract(order.getQuantityRemaining())).fee(order.getCommissionPaid()).orderStatus(status).build();
}
