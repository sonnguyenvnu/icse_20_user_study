private String getMetricsCacheName(String storeName){
  if (!configuration.get(BASIC_METRICS))   return null;
  return configuration.get(METRICS_MERGE_STORES) ? METRICS_MERGED_CACHE : storeName + METRICS_CACHE_SUFFIX;
}
