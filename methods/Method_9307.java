private LiveLocation addUserMarker(TLRPC.Message message){
  LiveLocation liveLocation;
  LatLng latLng=new LatLng(message.media.geo.lat,message.media.geo._long);
  if ((liveLocation=markersMap.get(message.from_id)) == null) {
    liveLocation=new LiveLocation();
    liveLocation.object=message;
    if (liveLocation.object.from_id != 0) {
      liveLocation.user=MessagesController.getInstance(currentAccount).getUser(liveLocation.object.from_id);
      liveLocation.id=liveLocation.object.from_id;
    }
 else {
      int did=(int)MessageObject.getDialogId(message);
      if (did > 0) {
        liveLocation.user=MessagesController.getInstance(currentAccount).getUser(did);
        liveLocation.id=did;
      }
 else {
        liveLocation.chat=MessagesController.getInstance(currentAccount).getChat(-did);
        liveLocation.id=did;
      }
    }
    try {
      MarkerOptions options=new MarkerOptions().position(latLng);
      Bitmap bitmap=createUserBitmap(liveLocation);
      if (bitmap != null) {
        options.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        options.anchor(0.5f,0.907f);
        liveLocation.marker=googleMap.addMarker(options);
        markers.add(liveLocation);
        markersMap.put(liveLocation.id,liveLocation);
        LocationController.SharingLocationInfo myInfo=LocationController.getInstance(currentAccount).getSharingLocationInfo(dialogId);
        if (liveLocation.id == UserConfig.getInstance(currentAccount).getClientUserId() && myInfo != null && liveLocation.object.id == myInfo.mid && myLocation != null) {
          liveLocation.marker.setPosition(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()));
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else {
    liveLocation.object=message;
    liveLocation.marker.setPosition(latLng);
  }
  return liveLocation;
}
