@Override public void onLocationChanged(Location location){
  if (location == null) {
    return;
  }
  mCurrentBestLocation=location;
  if (mBearing != null) {
    mCurrentBestLocation.setBearing(mBearing);
  }
  mLocationCallback.handleNewLocation(mCurrentBestLocation);
}
