private OpenOrders convertOpenOrders(Collection<OrderResponse> orders,OpenOrdersParams params){
  Builder<LimitOrder> openOrders=ImmutableList.builder();
  Builder<Order> hiddenOrders=ImmutableList.builder();
  orders.stream().map(KucoinAdapters::adaptOrder).filter(o -> params == null ? true : params.accept(o)).forEach(o -> {
    if (o instanceof LimitOrder) {
      openOrders.add((LimitOrder)o);
    }
 else {
      hiddenOrders.add(o);
    }
  }
);
  return new OpenOrders(openOrders.build(),hiddenOrders.build());
}
