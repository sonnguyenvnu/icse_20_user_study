private void positionMarker(Location location){
  if (location == null) {
    return;
  }
  myLocation=new Location(location);
  LiveLocation liveLocation=markersMap.get(UserConfig.getInstance(currentAccount).getClientUserId());
  LocationController.SharingLocationInfo myInfo=LocationController.getInstance(currentAccount).getSharingLocationInfo(dialogId);
  if (liveLocation != null && myInfo != null && liveLocation.object.id == myInfo.mid) {
    liveLocation.marker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
  }
  if (messageObject == null && googleMap != null) {
    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
    if (adapter != null) {
      if (adapter.isPulledUp()) {
        adapter.searchPlacesWithQuery(null,myLocation,true);
      }
      adapter.setGpsLocation(myLocation);
    }
    if (!userLocationMoved) {
      userLocation=new Location(location);
      if (firstWas) {
        CameraUpdate position=CameraUpdateFactory.newLatLng(latLng);
        googleMap.animateCamera(position);
      }
 else {
        firstWas=true;
        CameraUpdate position=CameraUpdateFactory.newLatLngZoom(latLng,googleMap.getMaxZoomLevel() - 4);
        googleMap.moveCamera(position);
      }
    }
  }
 else {
    adapter.setGpsLocation(myLocation);
  }
}
