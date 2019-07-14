@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  String type=limitOrder.getType().equals(Order.OrderType.BID) ? "buy" : "sell";
  return placeOrder(type,limitOrder.getLimitPrice(),limitOrder.getCurrencyPair(),limitOrder.getOriginalAmount());
}
