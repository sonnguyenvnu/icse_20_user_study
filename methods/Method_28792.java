public void discoverClusterNodesAndSlots(Jedis jedis){
  w.lock();
  try {
    this.nodes.clear();
    this.slots.clear();
    String localNodes=jedis.clusterNodes();
    for (    String nodeInfo : localNodes.split("\n")) {
      ClusterNodeInformation clusterNodeInfo=nodeInfoParser.parse(nodeInfo,new HostAndPort(jedis.getClient().getHost(),jedis.getClient().getPort()));
      HostAndPort targetNode=clusterNodeInfo.getNode();
      setNodeIfNotExist(targetNode);
      assignSlotsToNode(clusterNodeInfo.getAvailableSlots(),targetNode);
    }
  }
  finally {
    w.unlock();
  }
}
