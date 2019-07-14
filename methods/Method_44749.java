public final UserTrades getOrderTrades(Order order) throws IOException {
  return getOrderTrades(order.getId(),order.getCurrencyPair());
}
