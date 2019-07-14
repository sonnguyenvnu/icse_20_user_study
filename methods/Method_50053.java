/** 
 * Checks whether or not mobile data is enabled and returns the result
 * @param context is the context of the activity or service
 * @return true if data is enabled or false if disabled
 */
public static Boolean isMobileDataEnabled(Context context){
  ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  try {
    Class<?> c=Class.forName(cm.getClass().getName());
    Method m=c.getDeclaredMethod("getMobileDataEnabled");
    m.setAccessible(true);
    return (Boolean)m.invoke(cm);
  }
 catch (  Exception e) {
    Timber.e(e,"exception thrown");
    return null;
  }
}
