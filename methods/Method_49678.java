@Override public void onConnected(Bundle bundle){
  Log.i(TAG,"Connected to location services");
  LocationAvailability locationAvailability=mFusedLocationProviderApi.getLocationAvailability(mGoogleApiClient);
  if (!locationAvailability.isLocationAvailable()) {
    mLocationCallback.handleLocationNotAvailable();
    return;
  }
  Location lastKnownLocation=mFusedLocationProviderApi.getLastLocation(mGoogleApiClient);
  mFusedLocationProviderApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
  if (lastKnownLocation != null) {
    Log.i(TAG,"Received last known location: " + lastKnownLocation);
    mCurrentBestLocation=lastKnownLocation;
    if (mBearing != null) {
      mCurrentBestLocation.setBearing(mBearing);
    }
    mLocationCallback.handleNewLocation(mCurrentBestLocation);
  }
}
