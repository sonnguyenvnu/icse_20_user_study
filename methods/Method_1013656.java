@Override public CuratorFramework create(String address) throws InterruptedException {
  Builder builder=CuratorFrameworkFactory.builder();
  builder.connectionTimeoutMs(getZkConnectionTimeoutMillis());
  builder.connectString(address);
  builder.maxCloseWaitMs(getZkCloseWaitMillis());
  builder.namespace(getZkNamespace());
  builder.retryPolicy(new RetryNTimes(getZkRetries(),getSleepMsBetweenRetries()));
  builder.sessionTimeoutMs(getZkSessionTimeoutMillis());
  builder.threadFactory(XpipeThreadFactory.create("Xpipe-ZK-" + address,true));
  logger.info("[create]{}, {}",Codec.DEFAULT.encode(this),address);
  CuratorFramework curatorFramework=builder.build();
  curatorFramework.start();
  curatorFramework.blockUntilConnected(waitForZkConnectedMillis(),TimeUnit.MILLISECONDS);
  return curatorFramework;
}
