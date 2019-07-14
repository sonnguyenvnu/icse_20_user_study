/** 
 * ???????ID
 * @param context
 * @return
 */
public static String getSubscriberId(Context context){
  TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
    RxToast.error("??????????????");
    return null;
  }
  return tm.getSubscriberId();
}
