/** 
 * Start a new  {@link NetworkRequest} for MMS
 */
private void newRequest(){
  final ConnectivityManager connectivityManager=getConnectivityManager();
  mNetworkCallback=new ConnectivityManager.NetworkCallback(){
    @Override public void onAvailable(    Network network){
      super.onAvailable(network);
      Timber.d("NetworkCallbackListener.onAvailable: network=" + network);
synchronized (MmsNetworkManager.this) {
        mNetwork=network;
        MmsNetworkManager.this.notifyAll();
      }
    }
    @Override public void onLost(    Network network){
      super.onLost(network);
      Timber.d("NetworkCallbackListener.onLost: network=" + network);
synchronized (MmsNetworkManager.this) {
        releaseRequestLocked(this);
        MmsNetworkManager.this.notifyAll();
      }
    }
  }
;
  try {
    connectivityManager.requestNetwork(mNetworkRequest,mNetworkCallback);
  }
 catch (  SecurityException e) {
    Timber.e(e,"permission exception... skipping it for testing purposes");
    permissionError=true;
  }
}
