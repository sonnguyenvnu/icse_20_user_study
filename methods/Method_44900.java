@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  return super.limitOrder(limitOrder).getUuid();
}
