@Override public Long getSchemaId(String schemaName){
  incAction(METRICS_TYPENAME,CacheMetricsAction.RETRIEVAL);
  return cache.getSchemaId(schemaName);
}
