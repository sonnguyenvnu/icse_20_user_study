public void _XXXXX_(URI uri) throws IOException {
  DistributedLogConfiguration conf=new DistributedLogConfiguration();
  ZooKeeperClient zkc=ZooKeeperClientBuilder.newBuilder().sessionTimeoutMs(conf.getZKSessionTimeoutMilliseconds()).retryThreadCount(conf.getZKClientNumberRetryThreads()).requestRateLimit(conf.getZKRequestRateLimit()).zkAclId(conf.getZkAclId()).uri(uri).build();
  byte[] data=serialize();
  try {
    zkc.get().setData(uri.getPath(),data,-1);
  }
 catch (  KeeperException e) {
    throw new IOException("Fail to update dl metadata " + new String(data,UTF_8) + " to uri "+ uri,e);
  }
catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
    throw new IOException("Interrupted when updating dl metadata " + new String(data,UTF_8) + " to uri "+ uri,e);
  }
 finally {
    zkc.close();
  }
}