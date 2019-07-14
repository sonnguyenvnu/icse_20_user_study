@Override public void deleteLocks(StoreTransaction tx) throws TemporaryLockingException, PermanentLockingException {
  if (null != tx.getConfiguration().getGroupName()) {
    MetricManager.INSTANCE.getCounter(tx.getConfiguration().getGroupName(),M_LOCKS,M_DELETE,M_CALLS).inc();
  }
  Map<KeyColumn,S> m=lockState.getLocksForTx(tx);
  final Iterator<Map.Entry<KeyColumn,S>> iterator=m.entrySet().iterator();
  while (iterator.hasNext()) {
    final Map.Entry<KeyColumn,S> entry=iterator.next();
    final KeyColumn kc=entry.getKey();
    final S ls=entry.getValue();
    try {
      deleteSingleLock(kc,ls,tx);
    }
 catch (    AssertionError ae) {
      throw ae;
    }
catch (    Throwable t) {
      log.error("Exception while deleting lock on " + kc,t);
      if (null != tx.getConfiguration().getGroupName()) {
        MetricManager.INSTANCE.getCounter(tx.getConfiguration().getGroupName(),M_LOCKS,M_DELETE,M_CALLS).inc();
      }
    }
    llm.unlock(kc,tx);
    iterator.remove();
  }
}
