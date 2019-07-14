/** 
 * ????????
 * @param context
 * @return
 */
public static boolean isNetWorkConnected(Context context){
  if (context != null) {
    ConnectivityManager mConnectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
    if (mNetworkInfo != null) {
      return mNetworkInfo.isConnected();
    }
  }
  return false;
}
