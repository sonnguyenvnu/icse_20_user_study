public void currentLocationUpdated(Location location){
  if (location.hasBearing() && getCurrentOpenLocationCode() != null) {
    Direction direction=DirectionUtil.getDirection(location,getCurrentOpenLocationCode());
    mDirectionActionsListener.directionUpdated(direction);
  }
  if (mCurrentLocation == null) {
    mMapActionsListener.setMapCameraPosition(location.getLatitude(),location.getLongitude(),MyMapView.INITIAL_MAP_ZOOM);
  }
  mCurrentLocation=location;
}
