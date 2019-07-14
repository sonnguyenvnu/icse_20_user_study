@Override public int dtxState(String groupId){
  return this.attachmentCache.containsKey(groupId,"rollback-only") ? 0 : 1;
}
