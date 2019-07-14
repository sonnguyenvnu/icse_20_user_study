@Override public Collection<Order> getOrder(final String... orderIds) throws IOException {
  final List<Order> orders=new ArrayList<>();
  for (  final String orderId : orderIds) {
    orders.add(LiquiAdapters.adaptOrderInfo(getOrderInfo(Long.parseLong(orderId))));
  }
  return orders;
}
