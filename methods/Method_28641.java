@Override public void subscribe(BinaryJedisPubSub jedisPubSub,byte[]... channels){
  client.setTimeoutInfinite();
  try {
    jedisPubSub.proceed(client,channels);
  }
  finally {
    client.rollbackTimeout();
  }
}
