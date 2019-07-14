protected void updateNetworkType(){
  ConnectivityManager cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
  NetworkInfo info=cm.getActiveNetworkInfo();
  lastNetInfo=info;
  int type=VoIPController.NET_TYPE_UNKNOWN;
  if (info != null) {
switch (info.getType()) {
case ConnectivityManager.TYPE_MOBILE:
switch (info.getSubtype()) {
case TelephonyManager.NETWORK_TYPE_GPRS:
        type=VoIPController.NET_TYPE_GPRS;
      break;
case TelephonyManager.NETWORK_TYPE_EDGE:
case TelephonyManager.NETWORK_TYPE_1xRTT:
    type=VoIPController.NET_TYPE_EDGE;
  break;
case TelephonyManager.NETWORK_TYPE_UMTS:
case TelephonyManager.NETWORK_TYPE_EVDO_0:
type=VoIPController.NET_TYPE_3G;
break;
case TelephonyManager.NETWORK_TYPE_HSDPA:
case TelephonyManager.NETWORK_TYPE_HSPA:
case TelephonyManager.NETWORK_TYPE_HSPAP:
case TelephonyManager.NETWORK_TYPE_HSUPA:
case TelephonyManager.NETWORK_TYPE_EVDO_A:
case TelephonyManager.NETWORK_TYPE_EVDO_B:
type=VoIPController.NET_TYPE_HSPA;
break;
case TelephonyManager.NETWORK_TYPE_LTE:
type=VoIPController.NET_TYPE_LTE;
break;
default :
type=VoIPController.NET_TYPE_OTHER_MOBILE;
break;
}
break;
case ConnectivityManager.TYPE_WIFI:
type=VoIPController.NET_TYPE_WIFI;
break;
case ConnectivityManager.TYPE_ETHERNET:
type=VoIPController.NET_TYPE_ETHERNET;
break;
}
}
if (controller != null) {
controller.setNetworkType(type);
}
}
