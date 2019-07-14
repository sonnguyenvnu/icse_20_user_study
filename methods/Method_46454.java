@Override public Set<String> findTxcLockSet(String groupId,String unitId) throws TCGlobalContextException {
  String lockKey=unitId + ".txc.lock";
  if (attachmentCache.containsKey(groupId,lockKey)) {
    return attachmentCache.attachment(groupId,lockKey);
  }
  throw new TCGlobalContextException("non exists lock id.");
}
