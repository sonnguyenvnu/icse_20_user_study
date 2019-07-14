/** 
 * If  {@code !}{@link #isMutationStarted()}, check all locks and expected values, then mark the transaction as started. <p> If  {@link #isMutationStarted()}, this does nothing.
 * @throws org.janusgraph.diskstorage.BackendException
 * @return true if this transaction holds at least one lock, false if thetransaction holds no locks
 */
boolean prepareForMutations() throws BackendException {
  if (!isMutationStarted()) {
    checkAllLocks();
    checkAllExpectedValues();
    mutationStarted();
  }
  return !expectedValuesByStore.isEmpty();
}
