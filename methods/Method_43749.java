@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  CoindealOrder order=placeOrder(limitOrder);
  return order.getClientOrderId();
}
