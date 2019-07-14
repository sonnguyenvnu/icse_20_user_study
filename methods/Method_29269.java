@Override public String getNodeId(long appId,String ip,int port){
  final Jedis jedis=getJedis(appId,ip,port);
  try {
    final StringBuilder clusterNodes=new StringBuilder();
    boolean isGetNodes=new IdempotentConfirmer(){
      @Override public boolean execute(){
        String nodes=jedis.clusterNodes();
        if (nodes != null && nodes.length() > 0) {
          clusterNodes.append(nodes);
          return true;
        }
        return false;
      }
    }
.run();
    if (!isGetNodes) {
      logger.error("{}:{} clusterNodes failed",jedis.getClient().getHost(),jedis.getClient().getPort());
      return null;
    }
    for (    String infoLine : clusterNodes.toString().split("\n")) {
      if (infoLine.contains("myself")) {
        String nodeId=infoLine.split(" ")[0];
        return nodeId;
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (jedis != null)     jedis.close();
  }
  return null;
}
