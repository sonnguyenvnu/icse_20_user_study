@SuppressWarnings("unchecked") public static void endIncomingCall(){
  if (!hasCallPermissions) {
    return;
  }
  try {
    TelephonyManager tm=(TelephonyManager)ApplicationLoader.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
    Class c=Class.forName(tm.getClass().getName());
    Method m=c.getDeclaredMethod("getITelephony");
    m.setAccessible(true);
    ITelephony telephonyService=(ITelephony)m.invoke(tm);
    telephonyService=(ITelephony)m.invoke(tm);
    telephonyService.silenceRinger();
    telephonyService.endCall();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
