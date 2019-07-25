@Override public Iterator<ShardRouting> iterator(){
  return Collections.unmodifiableCollection(shards.values()).iterator();
}
