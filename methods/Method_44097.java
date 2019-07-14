public Depth marketDepth(long symbolId,int count) throws DragonexException, IOException {
  DragonResult<Depth> marketDepth=exchange.dragonexPublic().marketDepth(symbolId,count);
  return marketDepth.getResult();
}
