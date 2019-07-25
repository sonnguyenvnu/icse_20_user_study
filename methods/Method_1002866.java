/** 
 * Returns a newly-created  {@link ServerListener} based on the {@link Runnable}s added to this builder.
 */
public ServerListener build(){
  return new CallbackServerListener(serverStartingCallbacks,serverStartedCallbacks,serverStoppingCallbacks,serverStoppedCallbacks);
}
