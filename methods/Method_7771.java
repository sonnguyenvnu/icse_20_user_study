public void setGpsLocation(Location location){
  boolean notSet=gpsLocation == null;
  gpsLocation=location;
  if (notSet && shareLiveLocationPotistion > 0) {
    notifyItemChanged(shareLiveLocationPotistion);
  }
  if (currentMessageObject != null) {
    notifyItemChanged(1);
    updateLiveLocations();
  }
 else   if (liveLocationType != 2) {
    updateCell();
  }
 else {
    updateLiveLocations();
  }
}
