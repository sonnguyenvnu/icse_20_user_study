/** 
 * ?????IMEI
 * @param context
 * @return
 */
@SuppressLint("HardwareIds") public static String getDeviceIdIMEI(Context context){
  String id;
  TelephonyManager mTelephony=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
    RxToast.error("????????????");
    return null;
  }
  if (mTelephony.getDeviceId() != null) {
    id=mTelephony.getDeviceId();
  }
 else {
    id=Settings.Secure.getString(context.getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
  }
  return id;
}
