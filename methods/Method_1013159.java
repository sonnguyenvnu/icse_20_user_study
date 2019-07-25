/** 
 * Disconnect the source from the listener
 */
public synchronized void disconnect(ITLCOutputListener listener){
  Assert.isNotNull(listener);
  ITLCOutputSource source=this.sources.get(listener.getModel());
  if (source != null) {
    source.removeTLCOutputListener(listener);
  }
  printStats();
}
