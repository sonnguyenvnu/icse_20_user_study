public String getNodeId(final long appId,final Jedis jedis){
  String nodeKey=getNodeKey(jedis);
  if (nodeIdCachedMap.get(nodeKey) != null) {
    return nodeIdCachedMap.get(nodeKey);
  }
 else {
    String nodeId=redisCenter.getNodeId(appId,jedis.getClient().getHost(),jedis.getClient().getPort());
    nodeIdCachedMap.put(nodeKey,nodeId);
    return nodeId;
  }
}
