@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  final LakeBTCOrderResponse response=placeLakeBTCLimitOrder(limitOrder);
  return response.getId();
}
