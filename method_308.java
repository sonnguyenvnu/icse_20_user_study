/** 
 * Create the federated namespace.
 * @param namespace namespace to create
 * @param zkc zookeeper client
 * @throws InterruptedException
 * @throws ZooKeeperClient.ZooKeeperConnectionException
 * @throws KeeperException
 */
public static void _XXXXX_(URI namespace,ZooKeeperClient zkc) throws IOException, KeeperException {
  String zkSubNamespacesPath=namespace.getPath() + "/" + ZNODE_SUB_NAMESPACES;
  Utils.zkCreateFullPathOptimistic(zkc,zkSubNamespacesPath,new byte[0],zkc.getDefaultACL(),CreateMode.PERSISTENT);
}