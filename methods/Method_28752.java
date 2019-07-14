@Override public void subscribe(final JedisPubSub jedisPubSub,final String... channels){
  client.setTimeoutInfinite();
  try {
    jedisPubSub.proceed(client,channels);
  }
  finally {
    client.rollbackTimeout();
  }
}
