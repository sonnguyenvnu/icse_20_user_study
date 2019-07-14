private LocationListener createLocationListener(){
  return new LocationListener(){
    public void onLocationChanged(    Location location){
      if (LocationUtil.isBetterLocation(location,mCurrentBestLocation)) {
        mCurrentBestLocation=location;
        if (mBearing != null) {
          mCurrentBestLocation.setBearing(mBearing);
        }
        mLocationCallback.handleNewLocation(location);
      }
    }
    public void onStatusChanged(    String provider,    int status,    Bundle extras){
    }
    public void onProviderEnabled(    String provider){
    }
    public void onProviderDisabled(    String provider){
    }
  }
;
}
