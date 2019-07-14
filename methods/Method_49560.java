@Override public int getEstimatedRegionServerCount(){
  int serverCount=-1;
  try {
    serverCount=adm.getClusterStatus().getServers().size();
    log.debug("Read {} servers from HBase ClusterStatus",serverCount);
  }
 catch (  IOException e) {
    log.debug("Unable to retrieve HBase cluster status",e);
  }
  return serverCount;
}
