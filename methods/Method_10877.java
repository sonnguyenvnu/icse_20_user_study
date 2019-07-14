/** 
 * ???????4G ?????
 * @code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */
public static boolean is4G(Context context){
  NetworkInfo info=getActiveNetworkInfo(context);
  return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
}
