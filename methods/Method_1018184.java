public static NiFiFlowCacheSync EMPTY(String syncId){
  NiFiFlowCacheSync empty=new NiFiFlowCacheSync();
  empty.setSyncId(syncId);
  return empty;
}
