/** 
 * Sends a postMessage request using the origin communicated via {@link CustomTabsService#requestPostMessageChannel(CustomTabsSessionToken,Uri)}. Fails when called before {@link PostMessageServiceConnection#notifyMessageChannelReady(Bundle)} is received onthe client side.
 * @param message The message that is being sent.
 * @param extras Reserved for future use.
 * @return An integer constant about the postMessage request result. Will return{@link CustomTabsService#RESULT_SUCCESS} if successful.
 */
public int postMessage(String message,Bundle extras){
synchronized (mLock) {
    try {
      return mService.postMessage(mCallback,message,extras);
    }
 catch (    RemoteException e) {
      return CustomTabsService.RESULT_FAILURE_REMOTE_ERROR;
    }
  }
}
