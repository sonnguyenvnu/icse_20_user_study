public void sendCacheEviction(Set<JanusGraphSchemaVertex> updatedTypes,final boolean evictGraphFromCache,List<Callable<Boolean>> updatedTypeTriggers,Set<String> openInstances){
  Preconditions.checkArgument(!openInstances.isEmpty());
  long evictionId=evictionTriggerCounter.incrementAndGet();
  evictionTriggerMap.put(evictionId,new EvictionTrigger(evictionId,updatedTypeTriggers,graph));
  DataOutput out=graph.getDataSerializer().getDataOutput(128);
  out.writeObjectNotNull(MgmtLogType.CACHED_TYPE_EVICTION);
  VariableLong.writePositive(out,evictionId);
  VariableLong.writePositive(out,updatedTypes.size());
  for (  JanusGraphSchemaVertex type : updatedTypes) {
    assert type.hasId();
    VariableLong.writePositive(out,type.longId());
  }
  if (evictGraphFromCache) {
    out.writeObjectNotNull(EVICT);
  }
 else {
    out.writeObjectNotNull(DO_NOT_EVICT);
  }
  sysLog.add(out.getStaticBuffer());
}
