private void stop(boolean empty){
  started=false;
  locationManager.removeUpdates(gpsLocationListener);
  if (empty) {
    locationManager.removeUpdates(networkLocationListener);
    locationManager.removeUpdates(passiveLocationListener);
  }
}
