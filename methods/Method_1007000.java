@Override public Object[] extract(Order order){
  return new Object[]{"BEGIN_ORDER:",order.getOrderId(),dateFormat.format(order.getOrderDate())};
}
