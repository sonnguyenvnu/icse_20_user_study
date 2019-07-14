@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  return BitflyerAdapters.adaptOrderId(super.sendChildOrder(limitOrder));
}
