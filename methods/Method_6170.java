public static boolean isConnectionSlow(){
  try {
    ConnectivityManager connectivityManager=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo=connectivityManager.getActiveNetworkInfo();
    if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
switch (netInfo.getSubtype()) {
case TelephonyManager.NETWORK_TYPE_1xRTT:
case TelephonyManager.NETWORK_TYPE_CDMA:
case TelephonyManager.NETWORK_TYPE_EDGE:
case TelephonyManager.NETWORK_TYPE_GPRS:
case TelephonyManager.NETWORK_TYPE_IDEN:
        return true;
    }
  }
}
 catch (Throwable ignore) {
}
return false;
}
