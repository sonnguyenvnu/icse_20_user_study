public void checkConnection(){
  native_setUseIpv6(currentAccount,useIpv6Address());
  native_setNetworkAvailable(currentAccount,ApplicationLoader.isNetworkOnline(),ApplicationLoader.getCurrentNetworkType(),ApplicationLoader.isConnectionSlow());
}
