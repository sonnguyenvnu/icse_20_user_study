/** 
 * Subclasses could override if they need to do something specific, such as delete nodes, etc.
 * @param callback callback
 */
@Override public void shutdown(Callback<None> callback){
  debug(_log,"shutting down");
  callback.onSuccess(None.none());
  info(_log,"shutdown complete");
}
