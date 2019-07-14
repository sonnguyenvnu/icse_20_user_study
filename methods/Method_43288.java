@Override public Collection<Order> getOrder(String... orderIds) throws ExchangeException {
  String filter="{\"orderID\": [\"" + String.join("\",\"",orderIds) + "\"]}";
  List<BitmexPrivateOrder> privateOrders=getBitmexOrders(null,filter,null,null,null);
  return privateOrders.stream().map(BitmexAdapters::adaptOrder).collect(Collectors.toList());
}
