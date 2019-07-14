/** 
 * Notifies the client that the postMessage channel requested with {@link CustomTabsService#requestPostMessageChannel(CustomTabsSessionToken,android.net.Uri)} is ready. This method should becalled when the browser binds to the client side  {@link PostMessageService} and also readiesa connection to the web frame.
 * @param extras Reserved for future use.
 * @return Whether the notification was sent to the remote successfully.
 */
public final boolean notifyMessageChannelReady(Bundle extras){
  if (mService == null)   return false;
synchronized (mLock) {
    try {
      mService.onMessageChannelReady(mSessionBinder,extras);
    }
 catch (    RemoteException e) {
      return false;
    }
  }
  return true;
}
