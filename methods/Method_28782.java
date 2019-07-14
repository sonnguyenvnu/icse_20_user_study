public T run(int keyCount,String... keys){
  if (keys == null || keys.length == 0) {
    throw new JedisClusterException("No way to dispatch this command to Redis Cluster.");
  }
  if (keys.length > 1) {
    int slot=JedisClusterCRC16.getSlot(keys[0]);
    for (int i=1; i < keyCount; i++) {
      int nextSlot=JedisClusterCRC16.getSlot(keys[i]);
      if (slot != nextSlot) {
        throw new JedisClusterException("No way to dispatch this command to Redis Cluster " + "because keys have different slots.");
      }
    }
  }
  return runWithRetries(SafeEncoder.encode(keys[0]),this.redirections,false,false);
}
