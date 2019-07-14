@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  CexIOOrder order=placeCexIOLimitOrder(limitOrder);
  return Long.toString(order.getId());
}
