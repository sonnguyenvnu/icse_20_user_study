public void setGoogleMapLocation(Location location,boolean first){
  if (location == null) {
    return;
  }
  lastLocationByGoogleMaps=true;
  if (first || lastKnownLocation != null && lastKnownLocation.distanceTo(location) >= 20) {
    lastLocationSendTime=System.currentTimeMillis() - BACKGROUD_UPDATE_TIME;
    locationSentSinceLastGoogleMapUpdate=false;
  }
 else   if (locationSentSinceLastGoogleMapUpdate) {
    lastLocationSendTime=System.currentTimeMillis() - BACKGROUD_UPDATE_TIME + FOREGROUND_UPDATE_TIME;
    locationSentSinceLastGoogleMapUpdate=false;
  }
  lastKnownLocation=location;
}
