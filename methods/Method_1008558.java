/** 
 * Syncs the given location with the underlying storage unless already synced. This method might return immediately without actually fsyncing the location until the sync listener is called. Yet, unless there is already another thread fsyncing the transaction log the caller thread will be hijacked to run the fsync for all pending fsync operations. This method allows indexing threads to continue indexing without blocking on fsync calls. We ensure that there is only one thread blocking on the sync an all others can continue indexing. NOTE: if the syncListener throws an exception when it's processed the exception will only be logged. Users should make sure that the listener handles all exception cases internally.
 */
public final void sync(Translog.Location location,Consumer<Exception> syncListener){
  verifyNotClosed();
  translogSyncProcessor.put(location,syncListener);
}
