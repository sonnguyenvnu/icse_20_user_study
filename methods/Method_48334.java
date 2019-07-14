private List<Entry> getSliceWithRetries(KeySliceQuery ksq,StoreTransaction tx) throws BackendException {
  for (int i=0; i < lockRetryCount; i++) {
    try {
      return store.getSlice(ksq,tx);
    }
 catch (    PermanentBackendException e) {
      log.error("Failed to check locks",e);
      throw new PermanentLockingException(e);
    }
catch (    TemporaryBackendException e) {
      log.warn("Temporary storage failure while checking locks",e);
    }
  }
  throw new TemporaryBackendException("Maximum retries (" + lockRetryCount + ") exceeded while checking locks");
}
