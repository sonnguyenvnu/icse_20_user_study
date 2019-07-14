@Override public Collection<Order> getOrder(String... orderIds) throws IOException {
  Collection<Order> orders=new ArrayList<>(orderIds.length);
  Map<String,String> parameters=new HashMap<String,String>();
  StringBuilder builder=new StringBuilder();
  int k=1;
  for (  String orderId : orderIds) {
    builder.append(orderId);
    if (k > orderIds.length)     builder.append(",");
  }
  parameters.put("orderNoList",builder.toString());
  CoinsuperResponse<List<OrderList>> ordersList=orderList(parameters);
  for (  OrderList orderList : ordersList.getData().getResult()) {
    System.out.println(orderList.getOrderNo());
    System.out.println(orderList.getSymbol());
    orders.add(CoinsuperAdapters.adaptOrder(Long.toString(orderList.getOrderNo()),orderList));
  }
  return orders;
}
