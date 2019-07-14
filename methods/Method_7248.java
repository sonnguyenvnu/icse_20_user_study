/** 
 * Creates a new session through an ICustomTabsService with the optional callback. This session can be used to associate any related communication through the service with an intent and then later with a Custom Tab. The client can then send later service calls or intents to through same session-intent-Custom Tab association.
 * @param callback The callback through which the client will receive updates about the createdsession. Can be null. All the callbacks will be received on the UI thread.
 * @return The session object that was created as a result of the transaction. The client canuse this to relay session specific calls. Null on error.
 */
public CustomTabsSession newSession(final CustomTabsCallback callback){
  ICustomTabsCallback.Stub wrapper=new ICustomTabsCallback.Stub(){
    @Override public void onNavigationEvent(    final int navigationEvent,    final Bundle extras){
      if (callback == null)       return;
      mHandler.post(new Runnable(){
        @Override public void run(){
          callback.onNavigationEvent(navigationEvent,extras);
        }
      }
);
    }
    @Override public void extraCallback(    final String callbackName,    final Bundle args) throws RemoteException {
      if (callback == null)       return;
      mHandler.post(new Runnable(){
        @Override public void run(){
          callback.extraCallback(callbackName,args);
        }
      }
);
    }
    @Override public void onMessageChannelReady(    final Bundle extras) throws RemoteException {
      if (callback == null)       return;
      mHandler.post(new Runnable(){
        @Override public void run(){
          callback.onMessageChannelReady(extras);
        }
      }
);
    }
    @Override public void onPostMessage(    final String message,    final Bundle extras) throws RemoteException {
      if (callback == null)       return;
      mHandler.post(new Runnable(){
        @Override public void run(){
          callback.onPostMessage(message,extras);
        }
      }
);
    }
  }
;
  try {
    if (!mService.newSession(wrapper))     return null;
  }
 catch (  RemoteException e) {
    return null;
  }
  return new CustomTabsSession(mService,wrapper,mServiceComponentName);
}
