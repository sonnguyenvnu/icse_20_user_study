@ReactMethod public void isLocationEnabled(Promise p){
  boolean locationEnabled=false;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    LocationManager mLocationManager=(LocationManager)reactContext.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    locationEnabled=mLocationManager.isLocationEnabled();
  }
 else   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    int locationMode=Settings.Secure.getInt(reactContext.getContentResolver(),Settings.Secure.LOCATION_MODE,Settings.Secure.LOCATION_MODE_OFF);
    locationEnabled=locationMode != Settings.Secure.LOCATION_MODE_OFF;
  }
 else {
    String locationProviders=Settings.Secure.getString(reactContext.getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
    locationEnabled=!TextUtils.isEmpty(locationProviders);
  }
  p.resolve(locationEnabled);
}
