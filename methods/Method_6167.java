public static boolean isConnectedOrConnectingToWiFi(){
  try {
    ConnectivityManager connectivityManager=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    NetworkInfo.State state=netInfo.getState();
    if (netInfo != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING || state == NetworkInfo.State.SUSPENDED)) {
      return true;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
