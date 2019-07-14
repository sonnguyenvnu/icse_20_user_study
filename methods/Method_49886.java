/** 
 * Release the current  {@link NetworkRequest} for MMS
 * @param callback the {@link ConnectivityManager.NetworkCallback} to unregister
 */
private void releaseRequestLocked(ConnectivityManager.NetworkCallback callback){
  if (callback != null) {
    final ConnectivityManager connectivityManager=getConnectivityManager();
    try {
      connectivityManager.unregisterNetworkCallback(callback);
    }
 catch (    Exception e) {
      Timber.e(e,"couldn't unregister");
    }
  }
  resetLocked();
}
