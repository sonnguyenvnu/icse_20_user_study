/** 
 * Execute the given callback having optionally aquired the given lock. For <code>JobStoreTX</code>, because it manages its own transactions and only has the one datasource, this is the same behavior as  executeInNonManagedTXLock().
 * @param lockName The name of the lock to aquire, for example "TRIGGER_ACCESS".  If null, then no lock is aquired, but the lockCallback is still executed in a transaction.
 * @see JobStoreSupport#executeInNonManagedTXLock(String,TransactionCallback)
 * @see JobStoreCMT#executeInLock(String,TransactionCallback)
 * @see JobStoreSupport#getNonManagedTXConnection()
 * @see JobStoreSupport#getConnection()
 */
@Override protected Object executeInLock(String lockName,TransactionCallback txCallback) throws JobPersistenceException {
  return executeInNonManagedTXLock(lockName,txCallback,null);
}
