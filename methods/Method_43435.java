@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  if (limitOrder.getType() == OrderType.BID) {
    return buyLimit(limitOrder);
  }
 else {
    return sellLimit(limitOrder);
  }
}
