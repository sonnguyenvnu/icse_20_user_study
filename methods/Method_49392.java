@Override public void acquireLock(final StaticBuffer key,final StaticBuffer column,final StaticBuffer expectedValue,final StoreTransaction txh) throws BackendException {
  final boolean hasLocking=this.storeManager.getFeatures().hasLocking();
  if (!hasLocking) {
    throw new UnsupportedOperationException(String.format("%s doesn't support locking",getClass()));
  }
}
