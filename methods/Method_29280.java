private String getClusterNodeId(Jedis jedis){
  try {
    String infoOutput=jedis.clusterNodes();
    for (    String infoLine : infoOutput.split("\n")) {
      if (infoLine.contains("myself")) {
        return infoLine.split(" ")[0];
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return null;
}
