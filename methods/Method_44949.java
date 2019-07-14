@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  List<Order> orders=new ArrayList<>();
  for (  String orderId : orderIds) {
    Long id=Long.valueOf(orderId);
    BaseYoBitResponse response=service.orderInfo(exchange.getExchangeSpecification().getApiKey(),signatureCreator,"OrderInfo",exchange.getNonceFactory(),id);
    if (response.returnData != null) {
      Map map=(Map)response.returnData.get(orderId);
      Order order=YoBitAdapters.adaptOrder(orderId,map);
      orders.add(order);
    }
  }
  return orders;
}
