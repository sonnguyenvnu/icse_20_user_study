@Override public void writeLock(KeyColumn lockID,StoreTransaction tx) throws TemporaryLockingException, PermanentLockingException {
  if (null != tx.getConfiguration().getGroupName()) {
    MetricManager.INSTANCE.getCounter(tx.getConfiguration().getGroupName(),M_LOCKS,M_WRITE,M_CALLS).inc();
  }
  if (lockState.has(tx,lockID)) {
    log.debug("Transaction {} already wrote lock on {}",tx,lockID);
    return;
  }
  if (lockLocally(lockID,tx)) {
    boolean ok=false;
    try {
      S stat=writeSingleLock(lockID,tx);
      lockLocally(lockID,stat.getExpirationTimestamp(),tx);
      lockState.take(tx,lockID,stat);
      ok=true;
    }
 catch (    TemporaryBackendException tse) {
      throw new TemporaryLockingException(tse);
    }
catch (    AssertionError ae) {
      ok=true;
      throw ae;
    }
catch (    Throwable t) {
      throw new PermanentLockingException(t);
    }
 finally {
      if (!ok) {
        unlockLocally(lockID,tx);
        if (null != tx.getConfiguration().getGroupName()) {
          MetricManager.INSTANCE.getCounter(tx.getConfiguration().getGroupName(),M_LOCKS,M_WRITE,M_EXCEPTIONS).inc();
        }
      }
    }
  }
 else {
    throw new PermanentLockingException("Local lock contention");
  }
}
