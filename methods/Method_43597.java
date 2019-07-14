@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  List<Order> orders=new ArrayList<>();
  for (  String orderId : orderIds) {
    CexIOOpenOrder cexIOOrder=getOrderDetail(orderId);
    orders.add(CexIOAdapters.adaptOrder(cexIOOrder));
  }
  return orders;
}
