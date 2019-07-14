/** 
 * Execute the given callback having optionally acquired the given lock.   Because CMT assumes that the connection is already part of a managed transaction, it does not attempt to commit or rollback the  enclosing transaction.
 * @param lockName The name of the lock to acquire, for example "TRIGGER_ACCESS".  If null, then no lock is acquired, but the txCallback is still executed in a transaction.
 * @see JobStoreSupport#executeInNonManagedTXLock(String,TransactionCallback)
 * @see JobStoreTX#executeInLock(String,TransactionCallback)
 * @see JobStoreSupport#getNonManagedTXConnection()
 * @see JobStoreSupport#getConnection()
 */
@Override protected Object executeInLock(String lockName,TransactionCallback txCallback) throws JobPersistenceException {
  boolean transOwner=false;
  Connection conn=null;
  try {
    if (lockName != null) {
      if (getLockHandler().requiresConnection()) {
        conn=getConnection();
      }
      transOwner=getLockHandler().obtainLock(conn,lockName);
    }
    if (conn == null) {
      conn=getConnection();
    }
    return txCallback.execute(conn);
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
