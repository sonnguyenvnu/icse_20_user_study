/** 
 * Get the APN name for the active network
 * @return The APN name if available, otherwise null
 */
public String getApnName(){
  Network network=null;
synchronized (this) {
    if (mNetwork == null) {
      Timber.d("MmsNetworkManager: getApnName: network not available");
      mNetworkRequest=new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
      return null;
    }
    network=mNetwork;
  }
  String apnName=null;
  final ConnectivityManager connectivityManager=getConnectivityManager();
  NetworkInfo mmsNetworkInfo=connectivityManager.getNetworkInfo(network);
  if (mmsNetworkInfo != null) {
    apnName=mmsNetworkInfo.getExtraInfo();
  }
  Timber.d("MmsNetworkManager: getApnName: " + apnName);
  return apnName;
}
