/** 
 * ?????
 * @param context ???
 * @return ????
 * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/><p> ??????? 1 ???????? ???Wi-Fi, 2G, 3G, 4G? 2 ?????????????? 3 ?????????????? 4 ??API???????????????? <p> netTyped ???
 * @link #NETWORK_NO      = -1; ???????
 * @link #NETWORK_WIFI    =  1; wifi????
 * @link #NETWORK_2G      =  2; ???2G???
 * @link #NETWORK_3G      =  3; ???3G???
 * @link #NETWORK_4G      =  4; ???4G???
 * @link #NETWORK_UNKNOWN =  5; ????
 */
public static int getNetWorkType(Context context){
  ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo ni=cm.getActiveNetworkInfo();
  int netType=NETWORK_NO;
  if (ni != null && ni.isConnectedOrConnecting()) {
switch (ni.getType()) {
case ConnectivityManager.TYPE_WIFI:
      netType=NETWORK_WIFI;
    RxToast.success("???wifi???");
  break;
case ConnectivityManager.TYPE_MOBILE:
switch (ni.getSubtype()) {
case NETWORK_TYPE_GSM:
case TelephonyManager.NETWORK_TYPE_GPRS:
case TelephonyManager.NETWORK_TYPE_CDMA:
case TelephonyManager.NETWORK_TYPE_EDGE:
case TelephonyManager.NETWORK_TYPE_1xRTT:
case TelephonyManager.NETWORK_TYPE_IDEN:
  netType=NETWORK_2G;
RxToast.info("???2G???");
break;
case TelephonyManager.NETWORK_TYPE_EVDO_A:
case TelephonyManager.NETWORK_TYPE_UMTS:
case TelephonyManager.NETWORK_TYPE_EVDO_0:
case TelephonyManager.NETWORK_TYPE_HSDPA:
case TelephonyManager.NETWORK_TYPE_HSUPA:
case TelephonyManager.NETWORK_TYPE_HSPA:
case TelephonyManager.NETWORK_TYPE_EVDO_B:
case TelephonyManager.NETWORK_TYPE_EHRPD:
case TelephonyManager.NETWORK_TYPE_HSPAP:
case NETWORK_TYPE_TD_SCDMA:
netType=NETWORK_3G;
RxToast.info("???3G???");
break;
case TelephonyManager.NETWORK_TYPE_LTE:
case NETWORK_TYPE_IWLAN:
netType=NETWORK_4G;
RxToast.info("???4G???");
break;
default :
String subtypeName=ni.getSubtypeName();
if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
netType=NETWORK_3G;
}
 else {
netType=NETWORK_UNKNOWN;
}
RxToast.normal("????");
}
break;
default :
netType=5;
RxToast.normal("????");
}
}
 else {
netType=NETWORK_NO;
RxToast.error(context,"???????").show();
}
return netType;
}
