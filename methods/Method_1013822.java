@RequestMapping(path="/debug",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE) @Override public String debug(){
  JsonCodec pretty=new JsonCodec(true);
  return pretty.encode(new ClusterDebugInfo(currentClusterServer.getServerId(),dcMetaCache.getCurrentDc(),zkClient.getZkAddress(),metaServerConfig.getZkNameSpace(),currentClusterServer.isLeader(),currentClusterServer.getClusterInfo(),currentClusterServer.slots(),currentMetaServerMetaManager.allClusters(),clusterServers.allClusterServerInfos(),slotManager.allSlotsInfo()));
}
