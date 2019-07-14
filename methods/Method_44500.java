@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  return kucoinCreateOrder(KucoinAdapters.adaptLimitOrder(limitOrder)).getOrderId();
}
