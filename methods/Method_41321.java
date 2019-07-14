/** 
 * Execute the given callback having optionally acquired the given lock. This uses the non-managed transaction connection.
 * @param lockName The name of the lock to acquire, for example"TRIGGER_ACCESS".  If null, then no lock is acquired, but the lockCallback is still executed in a non-managed transaction. 
 */
protected <T>T executeInNonManagedTXLock(String lockName,TransactionCallback<T> txCallback,final TransactionValidator<T> txValidator) throws JobPersistenceException {
  boolean transOwner=false;
  Connection conn=null;
  try {
    if (lockName != null) {
      if (getLockHandler().requiresConnection()) {
        conn=getNonManagedTXConnection();
      }
      transOwner=getLockHandler().obtainLock(conn,lockName);
    }
    if (conn == null) {
      conn=getNonManagedTXConnection();
    }
    final T result=txCallback.execute(conn);
    try {
      commitConnection(conn);
    }
 catch (    JobPersistenceException e) {
      rollbackConnection(conn);
      if (txValidator == null || !retryExecuteInNonManagedTXLock(lockName,new TransactionCallback<Boolean>(){
        @Override public Boolean execute(        Connection conn) throws JobPersistenceException {
          return txValidator.validate(conn,result);
        }
      }
)) {
        throw e;
      }
    }
    Long sigTime=clearAndGetSignalSchedulingChangeOnTxCompletion();
    if (sigTime != null && sigTime >= 0) {
      signalSchedulingChangeImmediately(sigTime);
    }
    return result;
  }
 catch (  JobPersistenceException e) {
    rollbackConnection(conn);
    throw e;
  }
catch (  RuntimeException e) {
    rollbackConnection(conn);
    throw new JobPersistenceException("Unexpected runtime exception: " + e.getMessage(),e);
  }
 finally {
    try {
      releaseLock(lockName,transOwner);
    }
  finally {
      cleanupConnection(conn);
    }
  }
}
