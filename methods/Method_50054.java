/** 
 * Checks mobile data enabled based on telephonymanager
 * @param telephonyManager the telephony manager
 */
public static boolean isDataEnabled(TelephonyManager telephonyManager){
  try {
    Class<?> c=telephonyManager.getClass();
    Method m=c.getMethod("getDataEnabled");
    return (boolean)m.invoke(telephonyManager);
  }
 catch (  Exception e) {
    Timber.e(e,"exception thrown");
    return true;
  }
}
