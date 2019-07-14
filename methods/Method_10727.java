/** 
 * IMEI ????????? <p>?? {@link #isPhone(Context)}????</p> <p>?????  {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
 * @param context ???
 * @return IMEI
 */
public static String getIMEI(Context context){
  String deviceId;
  if (isPhone(context)) {
    deviceId=getDeviceIdIMEI(context);
  }
 else {
    deviceId=getAndroidId(context);
  }
  return deviceId;
}
