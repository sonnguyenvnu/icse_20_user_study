/** 
 * Notifies listeners that a transfer ended. 
 */
protected final void transferEnded(){
  DataSpec dataSpec=castNonNull(this.dataSpec);
  for (int i=0; i < listenerCount; i++) {
    listeners.get(i).onTransferEnd(this,dataSpec,isNetwork);
  }
  this.dataSpec=null;
}
