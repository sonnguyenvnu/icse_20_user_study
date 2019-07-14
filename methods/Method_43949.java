@Override default boolean accept(LimitOrder order){
  return order != null && getCurrencyPairs() != null && getCurrencyPairs().contains(order.getCurrencyPair());
}
