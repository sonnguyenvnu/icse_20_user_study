private void connectUsingOldApi(){
  Location lastKnownGpsLocation=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  Location lastKnownNetworkLocation=mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
  Location bestLastKnownLocation=mCurrentBestLocation;
  if (lastKnownGpsLocation != null && LocationUtil.isBetterLocation(lastKnownGpsLocation,bestLastKnownLocation)) {
    bestLastKnownLocation=lastKnownGpsLocation;
  }
  if (lastKnownNetworkLocation != null && LocationUtil.isBetterLocation(lastKnownNetworkLocation,bestLastKnownLocation)) {
    bestLastKnownLocation=lastKnownNetworkLocation;
  }
  mCurrentBestLocation=bestLastKnownLocation;
  if (mLocationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER) && mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,FASTEST_INTERVAL_IN_MS,0.0f,mGpsLocationListener);
  }
  if (mLocationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER) && mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,FASTEST_INTERVAL_IN_MS,0.0f,mNetworkLocationListener);
  }
  if (bestLastKnownLocation != null) {
    Log.i(TAG,"Received last known location via old API: " + bestLastKnownLocation);
    if (mBearing != null) {
      bestLastKnownLocation.setBearing(mBearing);
    }
    mLocationCallback.handleNewLocation(bestLastKnownLocation);
  }
}
