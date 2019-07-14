/** 
 * ?????
 * @param context
 * @return
 */
public static String getLine1Number(Context context){
  TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
    RxToast.error("????????????");
    return null;
  }
  return tm.getLine1Number();
}
