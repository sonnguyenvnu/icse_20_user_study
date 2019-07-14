public static boolean isConnectedToWiFi(){
  try {
    ConnectivityManager connectivityManager=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
      return true;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
