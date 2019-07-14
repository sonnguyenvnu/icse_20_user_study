@Override public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
  try {
    return placeBithumbMarketOrder(marketOrder).getOrderId();
  }
 catch (  BithumbException e) {
    throw BithumbErrorAdapter.adapt(e);
  }
}
