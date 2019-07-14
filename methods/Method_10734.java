/** 
 * ???????? <p>?????  {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
 * @param context ???
 * @return DeviceId(IMEI) = 99000311726612<br>DeviceSoftwareVersion = 00<br> Line1Number =<br> NetworkCountryIso = cn<br> NetworkOperator = 46003<br> NetworkOperatorName = ????<br> NetworkType = 6<br> honeType = 2<br> SimCountryIso = cn<br> SimOperator = 46003<br> SimOperatorName = ????<br> SimSerialNumber = 89860315045710604022<br> SimState = 5<br> SubscriberId(IMSI) = 460030419724900<br> VoiceMailNumber = *86<br>
 */
public static String getPhoneStatus(Context context){
  if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
    RxToast.error("??????????????");
    return null;
  }
  TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  String str="";
  str+="DeviceId(IMEI) = " + getDeviceIdIMEI(context) + "\n";
  str+="DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
  str+="Line1Number = " + tm.getLine1Number() + "\n";
  str+="NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
  str+="NetworkOperator = " + tm.getNetworkOperator() + "\n";
  str+="NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
  str+="NetworkType = " + tm.getNetworkType() + "\n";
  str+="honeType = " + tm.getPhoneType() + "\n";
  str+="SimCountryIso = " + tm.getSimCountryIso() + "\n";
  str+="SimOperator = " + tm.getSimOperator() + "\n";
  str+="SimOperatorName = " + tm.getSimOperatorName() + "\n";
  str+="SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
  str+="SimState = " + tm.getSimState() + "\n";
  str+="SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
  str+="VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
  return str;
}
