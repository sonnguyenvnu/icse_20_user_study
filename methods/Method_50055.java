/** 
 * Toggles mobile data
 * @param context is the context of the activity or service
 * @param enabled is whether to enable or disable data
 */
public static void setMobileDataEnabled(Context context,boolean enabled){
  try {
    TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    Class c=Class.forName(tm.getClass().getName());
    Method m=c.getDeclaredMethod("getITelephony");
    m.setAccessible(true);
    Object telephonyService=m.invoke(tm);
    c=Class.forName(telephonyService.getClass().getName());
    m=c.getDeclaredMethod("setDataEnabled",Boolean.TYPE);
    m.setAccessible(true);
    m.invoke(telephonyService,enabled);
  }
 catch (  Exception e) {
    Timber.e(e,"error enabling data on lollipop");
  }
}
