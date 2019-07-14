public static boolean isNetworkOnline(){
  try {
    ConnectivityManager connectivityManager=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo=connectivityManager.getActiveNetworkInfo();
    if (netInfo != null && (netInfo.isConnectedOrConnecting() || netInfo.isAvailable())) {
      return true;
    }
    netInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
      return true;
    }
 else {
      netInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      if (netInfo != null && netInfo.isConnectedOrConnecting()) {
        return true;
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return true;
  }
  return false;
}
