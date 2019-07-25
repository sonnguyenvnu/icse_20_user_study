public static void subscribe(Context context){
  lm=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
  boolean gps_enabled=false;
  try {
    gps_enabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
  boolean network_enabled=false;
  try {
    network_enabled=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
  if (gps_enabled) {
    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListenerGps);
  }
  if (network_enabled) {
    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListenerNetwork);
  }
}
