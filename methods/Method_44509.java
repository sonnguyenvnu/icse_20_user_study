protected LimitOrder mapKunaOrder2LimitOrder(KunaOrder kunaOrder,CurrencyPair currencyPair){
  Order.OrderType orderType=kunaOrder.getOrderType() == KunaOrderType.LIMIT ? Order.OrderType.ASK : Order.OrderType.BID;
  LimitOrder.Builder builder=new LimitOrder.Builder(orderType,currencyPair);
  builder.id(String.valueOf(kunaOrder.getId())).currencyPair(currencyPair).timestamp(kunaOrder.getCreatedAt()).orderStatus(Order.OrderStatus.NEW).orderType(orderType).averagePrice(kunaOrder.getAveragePrice()).limitPrice(kunaOrder.getPrice()).originalAmount(kunaOrder.getVolume()).remainingAmount(kunaOrder.getRemainingVolume()).cumulativeAmount(kunaOrder.getExecutedVolume());
  return builder.build();
}
