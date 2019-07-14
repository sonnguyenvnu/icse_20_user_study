/** 
 * GPS????
 * @param context
 * @return
 */
public static boolean isGpsEnabled(Context context){
  LocationManager lm=((LocationManager)context.getSystemService(Context.LOCATION_SERVICE));
  List<String> accessibleProviders=lm.getProviders(true);
  return accessibleProviders != null && accessibleProviders.size() > 0;
}
