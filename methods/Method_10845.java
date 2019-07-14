/** 
 * ????????
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isLocationEnabled(Context context){
  LocationManager lm=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
  return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
}
