/** 
 * It's assumed that the Persistent connection has been started outside of this class
 */
@Override public void start(Callback<None> callback){
  _afterStartupCallbacks.add(() -> callback.onSuccess(None.none()));
  fireAfterStartupCallbacks();
}
