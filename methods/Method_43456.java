@Override public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
  List<Long> orderIds=Arrays.stream(orderQueryParams).map(orderQueryParams1 -> Long.valueOf(orderQueryParams1.getOrderId())).collect(Collectors.toList());
  return getOrderDetails(orderIds).getOrders().stream().map(BTCMarketsAdapters::adaptOrder).collect(Collectors.toList());
}
