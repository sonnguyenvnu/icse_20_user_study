protected List<JedisPool> getShuffledNodesPool(){
  List<JedisPool> pools=new ArrayList<JedisPool>();
  pools.addAll(cache.getNodes().values());
  Collections.shuffle(pools);
  return pools;
}
