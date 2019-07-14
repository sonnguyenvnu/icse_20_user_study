private OpenOrders createOpenOrders(List<CoindirectOrder> coindirectOrders){
  List<LimitOrder> limitOrders=new ArrayList<>();
  List<Order> otherOrders=new ArrayList<>();
  coindirectOrders.forEach(coindirectOrder -> {
    Order order=CoindirectAdapters.adaptOrder(coindirectOrder);
    if (order instanceof LimitOrder) {
      limitOrders.add((LimitOrder)order);
    }
 else {
      otherOrders.add(order);
    }
  }
);
  return new OpenOrders(limitOrders,otherOrders);
}
