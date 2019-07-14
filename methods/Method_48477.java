@Override public EntryList getSchemaRelations(long schemaId,BaseRelationType type,Direction dir){
  incAction(METRICS_RELATIONS,CacheMetricsAction.RETRIEVAL);
  return cache.getSchemaRelations(schemaId,type,dir);
}
