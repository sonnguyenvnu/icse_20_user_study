void update(ShardRouting oldShard,ShardRouting newShard){
  if (shards.containsKey(oldShard.shardId()) == false) {
    return;
  }
  ShardRouting previousValue=shards.put(newShard.shardId(),newShard);
  assert previousValue == oldShard : "expected shard " + previousValue + " but was " + oldShard;
}
