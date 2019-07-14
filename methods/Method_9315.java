private void fetchRecentLocations(ArrayList<TLRPC.Message> messages){
  LatLngBounds.Builder builder=null;
  if (firstFocus) {
    builder=new LatLngBounds.Builder();
  }
  int date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  for (int a=0; a < messages.size(); a++) {
    TLRPC.Message message=messages.get(a);
    if (message.date + message.media.period > date) {
      if (builder != null) {
        LatLng latLng=new LatLng(message.media.geo.lat,message.media.geo._long);
        builder.include(latLng);
      }
      addUserMarker(message);
    }
  }
  if (builder != null) {
    firstFocus=false;
    adapter.setLiveLocations(markers);
    if (messageObject.isLiveLocation()) {
      try {
        final LatLngBounds bounds=builder.build();
        if (messages.size() > 1) {
          try {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,AndroidUtilities.dp(60)));
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
 catch (      Exception ignore) {
      }
    }
  }
}
