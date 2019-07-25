@Override public void close(){
  assert shardKeyMap.size() == 0 : shardKeyMap.size();
  assert shardStats.isEmpty() : shardStats.keySet();
  assert stats2.isEmpty() : stats2;
  cache.clear();
}
