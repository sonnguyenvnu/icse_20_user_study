/** 
 * lock data line
 * @param groupId   groupId
 * @param unitId    unitId
 * @param lockIdSet lockIdSet
 * @param isXLock   isXLock
 * @throws TxcLogicException ????
 */
private void lockDataLine(String groupId,String unitId,Set<String> lockIdSet,boolean isXLock) throws TxcLogicException {
  try {
    if (!reliableMessenger.acquireLocks(groupId,lockIdSet,isXLock ? DTXLocks.X_LOCK : DTXLocks.S_LOCK)) {
      throw new TxcLogicException("resource is locked! place try again later.");
    }
    globalContext.addTxcLockId(groupId,unitId,lockIdSet);
  }
 catch (  RpcException e) {
    throw new TxcLogicException("can't contact to any TM for lock info. default error.");
  }
}
