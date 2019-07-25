/** 
 * Returns a newly-created  {@link ZooKeeperUpdatingListener} instance that registers the server toZooKeeper when the server starts.
 */
public ZooKeeperUpdatingListener build(){
  final boolean internalClient;
  if (client == null) {
    client=CuratorFrameworkFactory.builder().connectString(connectionStr).retryPolicy(ZooKeeperDefaults.DEFAULT_RETRY_POLICY).connectionTimeoutMs(connectTimeoutMillis).sessionTimeoutMs(sessionTimeoutMillis).build();
    internalClient=true;
  }
 else {
    internalClient=false;
  }
  return new ZooKeeperUpdatingListener(client,zNodePath,nodeValueCodec,endpoint,internalClient);
}
