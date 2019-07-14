@Override public Serializable execute(TransactionCmd transactionCmd) throws TxManagerException {
  DTXLockParams dtxLockParams=transactionCmd.getMsg().loadBean(DTXLockParams.class);
  try {
    LockValue lockValue=new LockValue();
    lockValue.setGroupId(dtxLockParams.getGroupId());
    lockValue.setLockType(dtxLockParams.getLockType());
    fastStorage.acquireLocks(dtxLockParams.getContextId(),dtxLockParams.getLocks(),lockValue);
    return true;
  }
 catch (  FastStorageException e) {
    throw new TxManagerException(e);
  }
}
