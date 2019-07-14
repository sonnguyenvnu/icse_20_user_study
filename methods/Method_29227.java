public static String getConfig(int port,boolean isCluster){
  if (isCluster) {
    return String.format(CLUSTER_CONFIG,port);
  }
 else {
    return String.format(COMMON_CONFIG,port);
  }
}
