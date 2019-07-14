/** 
 * Notifies listeners that data transfer for the specified  {@link DataSpec} is being initialized.
 * @param dataSpec {@link DataSpec} describing the data for initializing transfer.
 */
protected final void transferInitializing(DataSpec dataSpec){
  for (int i=0; i < listenerCount; i++) {
    listeners.get(i).onTransferInitializing(this,dataSpec,isNetwork);
  }
}
