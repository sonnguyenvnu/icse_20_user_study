@Override public Iterator<IndexShardStats> iterator(){
  return getIndexShards().values().iterator();
}
