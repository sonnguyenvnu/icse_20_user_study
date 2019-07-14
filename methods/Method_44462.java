@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  return KrakenAdapters.adaptOrders(super.getOrders(orderIds));
}
