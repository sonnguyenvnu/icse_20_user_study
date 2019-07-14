private void initialize(List<S> shards){
  nodes=new TreeMap<Long,S>();
  for (int i=0; i != shards.size(); ++i) {
    final S shardInfo=shards.get(i);
    if (shardInfo.getName() == null)     for (int n=0; n < 160 * shardInfo.getWeight(); n++) {
      nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n),shardInfo);
    }
 else     for (int n=0; n < 160 * shardInfo.getWeight(); n++) {
      nodes.put(this.algo.hash(shardInfo.getName() + "*" + shardInfo.getWeight() + n),shardInfo);
    }
    resources.put(shardInfo,shardInfo.createResource());
  }
}
