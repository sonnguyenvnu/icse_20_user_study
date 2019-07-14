public HitbtcOrder getHitbtcOrder(String symbol,String clientOrderId) throws IOException {
  List<HitbtcOrder> orders=hitbtc.getHitbtcOrder(symbol,clientOrderId);
  if (orders == null || orders.isEmpty()) {
    return null;
  }
 else {
    return orders.iterator().next();
  }
}
