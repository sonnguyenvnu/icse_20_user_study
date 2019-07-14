private static String getUuid(){
  TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  String tmDevice, tmSerial, androidId;
  tmDevice=tm.getDeviceId().toString();
  tmSerial="ANDROID_ID";
  androidId=android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID).toString();
  UUID deviceUuid=new UUID(androidId.hashCode(),((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
  String uniqueId=deviceUuid.toString();
  return uniqueId;
}
