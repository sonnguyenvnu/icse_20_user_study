@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  try {
    List<Order> openOrders=new ArrayList<>();
    for (    String orderId : orderIds) {
      BitfinexOrderStatusResponse orderStatus=getBitfinexOrderStatus(orderId);
      BitfinexOrderStatusResponse[] orderStatuses=new BitfinexOrderStatusResponse[1];
      if (orderStatus != null) {
        orderStatuses[0]=orderStatus;
        OpenOrders orders=BitfinexAdapters.adaptOrders(orderStatuses);
        openOrders.add(orders.getOpenOrders().get(0));
      }
    }
    return openOrders;
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
