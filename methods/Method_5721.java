/** 
 * Notifies listeners that data transfer for the specified  {@link DataSpec} started.
 * @param dataSpec {@link DataSpec} describing the data being transferred.
 */
protected final void transferStarted(DataSpec dataSpec){
  this.dataSpec=dataSpec;
  for (int i=0; i < listenerCount; i++) {
    listeners.get(i).onTransferStart(this,dataSpec,isNetwork);
  }
}
