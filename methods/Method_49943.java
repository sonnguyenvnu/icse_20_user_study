protected int beginMmsConnectivity() throws IOException {
  Timber.v("beginMmsConnectivity");
  createWakeLock();
  if (Utils.isMmsOverWifiEnabled(this)) {
    NetworkInfo niWF=mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if ((niWF != null) && (niWF.isConnected())) {
      Timber.v("beginMmsConnectivity: Wifi active");
      return 0;
    }
  }
  int result=mConnMgr.startUsingNetworkFeature(ConnectivityManager.TYPE_MOBILE,"enableMMS");
  Timber.v("beginMmsConnectivity: result=" + result);
switch (result) {
case 0:
case 1:
    acquireWakeLock();
  return result;
}
throw new IOException("Cannot establish MMS connectivity");
}
