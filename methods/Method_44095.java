public List<Order> marketBuyOrders(long symbolId) throws DragonexException, IOException {
  DragonResult<List<Order>> marketBuyOrders=exchange.dragonexPublic().marketBuyOrders(symbolId);
  return marketBuyOrders.getResult();
}
