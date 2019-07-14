@Override default boolean accept(Order order){
  return order != null && (getCurrencyPair() == null || getCurrencyPair().equals(order.getCurrencyPair()));
}
