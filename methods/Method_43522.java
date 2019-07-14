public Collection<Order> getBxOrder(String... orderIds) throws IOException {
  List<Order> orders=new ArrayList<>();
  BxOrder[] bxOrders=getBxOrders();
  for (  BxOrder order : bxOrders) {
    for (    String orderId : orderIds) {
      if (order.getOrderId().equals(orderId)) {
        orders.add(BxAdapters.adaptOrder(order));
      }
    }
  }
  return orders;
}
