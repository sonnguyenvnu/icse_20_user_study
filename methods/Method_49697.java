@Override public void handleNewBearing(float bearing){
  Log.i(TAG,"Received new bearing from LocationProvider: " + bearing);
  if (mCurrentLocation != null) {
    mCurrentLocation.setBearing(bearing);
    currentLocationUpdated(mCurrentLocation);
  }
}
