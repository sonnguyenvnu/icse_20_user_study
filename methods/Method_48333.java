/** 
 * Try to write a lock record remotely up to the configured number of times. If the store produces {@link TemporaryLockingException}, then we'll call mutate again to add a new column with an updated timestamp and to delete the column that tried to write when the store threw an exception. We continue like that up to the retry limit. If the store throws anything else, such as an unchecked exception or a  {@link org.janusgraph.diskstorage.PermanentBackendException}, then we'll try to delete whatever we added and return without further retries.
 * @param lockID lock to acquire
 * @param txh    transaction
 * @return the timestamp, in nanoseconds since UNIX Epoch, on the lockcolumn that we successfully wrote to the store
 * @throws TemporaryLockingException if the lock retry count is exceeded without successfullywriting the lock in less than the wait limit
 * @throws Throwable                 if the storage layer throws anything else
 */
@Override protected ConsistentKeyLockStatus writeSingleLock(KeyColumn lockID,StoreTransaction txh) throws Throwable {
  final StaticBuffer lockKey=serializer.toLockKey(lockID.getKey(),lockID.getColumn());
  StaticBuffer oldLockCol=null;
  for (int i=0; i < lockRetryCount; i++) {
    WriteResult wr=tryWriteLockOnce(lockKey,oldLockCol,txh);
    if (wr.isSuccessful() && wr.getDuration().compareTo(lockWait) <= 0) {
      final Instant writeInstant=wr.getWriteTimestamp();
      final Instant expireInstant=writeInstant.plus(lockExpire);
      return new ConsistentKeyLockStatus(writeInstant,expireInstant);
    }
    oldLockCol=wr.getLockCol();
    handleMutationFailure(lockID,lockKey,wr,txh);
  }
  tryDeleteLockOnce(lockKey,oldLockCol,txh);
  throw new TemporaryBackendException("Lock write retry count exceeded");
}
