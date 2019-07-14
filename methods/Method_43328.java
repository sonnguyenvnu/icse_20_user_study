@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  Collection<Order> orders=new ArrayList<>(orderIds.length);
  for (  String orderId : orderIds) {
    orders.add(BitstampAdapters.adaptOrder(orderId,super.getBitstampOrder(Long.parseLong(orderId))));
  }
  return orders;
}
