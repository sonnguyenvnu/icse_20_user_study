/** 
 * Start the announcer. Needs to be called whenever there is a new zk session established.
 */
public synchronized void start(Callback<None> callback){
  if (_isUp) {
    markUp(callback);
  }
 else {
    callback.onSuccess(None.none());
  }
}
