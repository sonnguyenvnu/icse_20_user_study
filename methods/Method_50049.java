/** 
 * Gets the current users phone number
 * @param context is the context of the activity or service
 * @return a string of the phone number on the device
 */
public static String getMyPhoneNumber(Context context){
  TelephonyManager mTelephonyMgr;
  mTelephonyMgr=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  return mTelephonyMgr.getLine1Number();
}
