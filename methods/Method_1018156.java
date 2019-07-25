public static CacheSummary build(Map<String,NiFiFlowCacheSync> syncMap){
  Map<String,Integer> cacheIds=syncMap.entrySet().stream().collect(Collectors.toMap(stringNiFiFlowCacheSyncEntry -> stringNiFiFlowCacheSyncEntry.getKey(),stringNiFiFlowCacheSyncEntry1 -> stringNiFiFlowCacheSyncEntry1.getValue().getSnapshot().getProcessorIdToFeedNameMap().size()));
  return new CacheSummary(cacheIds);
}
