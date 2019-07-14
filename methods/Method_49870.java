private void loadDeviceUaSettings(Context context){
  final TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
  mUserAgent=telephonyManager.getMmsUserAgent();
  mUaProfUrl=telephonyManager.getMmsUAProfUrl();
}
