@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  try {
    return makeLimitOrder(limitOrder);
  }
 catch (  LivecoinException e) {
    throw LivecoinErrorAdapter.adapt(e);
  }
}
