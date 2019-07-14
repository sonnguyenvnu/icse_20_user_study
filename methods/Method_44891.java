public static OpenOrders adaptOrders(TheRockOrders theRockOrders){
  List<LimitOrder> orders=new ArrayList<>(theRockOrders.getOrders().length);
  for (  TheRockOrder theRockOrder : theRockOrders.getOrders()) {
    orders.add(adaptOrder(theRockOrder));
  }
  return new OpenOrders(orders);
}
