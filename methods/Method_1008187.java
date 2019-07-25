@Override public Iterator<ShardUpgradeStatus> iterator(){
  return Arrays.stream(shards).iterator();
}
