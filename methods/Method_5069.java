/** 
 * Stops watching for changes. 
 */
public void stop(){
  context.unregisterReceiver(receiver);
  receiver=null;
  if (networkCallback != null) {
    unregisterNetworkCallback();
  }
  logd(this + " stopped");
}
