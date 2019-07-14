@Override @SuppressWarnings("unchecked") public void addTxcLockId(String groupId,String unitId,Set<String> lockIdList){
  String lockKey=unitId + ".txc.lock";
  if (attachmentCache.containsKey(groupId,lockKey)) {
    ((Set)attachmentCache.attachment(groupId,lockKey)).addAll(lockIdList);
    return;
  }
  Set<String> lockList=new HashSet<>(lockIdList);
  attachmentCache.attach(groupId,lockKey,lockList);
}
