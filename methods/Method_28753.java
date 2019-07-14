@Override public void psubscribe(final JedisPubSub jedisPubSub,final String... patterns){
  checkIsInMultiOrPipeline();
  client.setTimeoutInfinite();
  try {
    jedisPubSub.proceedWithPatterns(client,patterns);
  }
  finally {
    client.rollbackTimeout();
  }
}
