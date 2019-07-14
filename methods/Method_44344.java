public static List<Order> adaptOrders(List<HuobiOrder> huobiOrders){
  List<Order> orders=new ArrayList<>();
  for (  HuobiOrder order : huobiOrders) {
    orders.add(adaptOrder(order));
  }
  return orders;
}
