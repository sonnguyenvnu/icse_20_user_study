@Override public void handleNewLocation(Location location){
  Log.i(TAG,"Received new location from LocationProvider: " + location);
  currentLocationUpdated(location);
}
