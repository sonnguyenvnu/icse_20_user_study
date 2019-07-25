@Override public List<FrontendAndBackendMetrics> analyze(ProxyChain chain){
  List<TunnelInfo> tunnelInfos=chain.getTunnels();
  if (tunnelInfos == null || tunnelInfos.isEmpty()) {
    logger.warn("[analyze] chain contains no tunnel info; cluster: {}, shard: {}",chain.getCluster(),chain.getShard());
    return EMPTY_METRICS;
  }
  List<FrontendAndBackendMetrics> result=Lists.newArrayList();
  String clusterId=chain.getCluster(), shardId=chain.getShard();
  for (  TunnelInfo info : tunnelInfos) {
    result.add(getMetrics(info,clusterId,shardId));
  }
  return result;
}
