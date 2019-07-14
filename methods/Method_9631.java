private void stopLocationUpdate(){
  updatingLocation=false;
  LocationManager locationManager=(LocationManager)ApplicationLoader.applicationContext.getSystemService(Context.LOCATION_SERVICE);
  locationManager.removeUpdates(gpsLocationListener);
  locationManager.removeUpdates(networkLocationListener);
}
