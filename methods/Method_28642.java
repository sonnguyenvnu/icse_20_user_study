@Override public void psubscribe(BinaryJedisPubSub jedisPubSub,byte[]... patterns){
  client.setTimeoutInfinite();
  try {
    jedisPubSub.proceedWithPatterns(client,patterns);
  }
  finally {
    client.rollbackTimeout();
  }
}
