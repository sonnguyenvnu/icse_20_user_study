public List<Order> marketSellOrders(long symbolId) throws DragonexException, IOException {
  DragonResult<List<Order>> marketSellOrders=exchange.dragonexPublic().marketSellOrders(symbolId);
  return marketSellOrders.getResult();
}
