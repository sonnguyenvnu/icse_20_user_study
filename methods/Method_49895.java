/** 
 * are we set up to use wifi? if so, send mms over it.
 */
public static boolean useWifi(Context context){
  if (Utils.isMmsOverWifiEnabled(context)) {
    ConnectivityManager mConnMgr=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (mConnMgr != null) {
      NetworkInfo niWF=mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      if ((niWF != null) && (niWF.isConnected())) {
        return true;
      }
    }
  }
  return false;
}
