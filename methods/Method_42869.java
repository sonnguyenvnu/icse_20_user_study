public LimitOrder mapOrder(CurrencyPair currencyPair,AcxOrder order){
  OrderType type=mapOrderType(order);
  return new LimitOrder.Builder(type,currencyPair).id(order.id).limitPrice(order.price).averagePrice(order.avgPrice).timestamp(order.createdAt).originalAmount(order.volume).remainingAmount(order.remainingVolume).cumulativeAmount(order.executedVolume).orderStatus(mapOrderStatus(order.state)).build();
}
