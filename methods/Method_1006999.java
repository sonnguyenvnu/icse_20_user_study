@Override public Object[] extract(Order order){
  return new Object[]{"END_ORDER:",order.getTotalPrice()};
}
