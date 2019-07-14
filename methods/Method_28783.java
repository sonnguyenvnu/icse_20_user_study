public T runBinary(byte[] key){
  if (key == null) {
    throw new JedisClusterException("No way to dispatch this command to Redis Cluster.");
  }
  return runWithRetries(key,this.redirections,false,false);
}
