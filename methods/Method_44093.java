public List<Coin> coinAll() throws DragonexException, IOException {
  DragonResult<List<Coin>> coinAll=exchange.dragonexPublic().coinAll();
  return coinAll.getResult();
}
