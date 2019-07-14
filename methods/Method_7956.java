private boolean isPhotoDataChanged(MessageObject object){
  if (object.type == 0 || object.type == 14) {
    return false;
  }
  if (object.type == 4) {
    if (currentUrl == null) {
      return true;
    }
    double lat=object.messageOwner.media.geo.lat;
    double lon=object.messageOwner.media.geo._long;
    String url;
    if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
      int photoWidth=backgroundWidth - AndroidUtilities.dp(21);
      int photoHeight=AndroidUtilities.dp(195);
      int offset=268435456;
      double rad=offset / Math.PI;
      double y=Math.round(offset - rad * Math.log((1 + Math.sin(lat * Math.PI / 180.0)) / (1 - Math.sin(lat * Math.PI / 180.0))) / 2) - (AndroidUtilities.dp(10.3f) << (21 - 15));
      lat=(Math.PI / 2.0 - 2 * Math.atan(Math.exp((y - offset) / rad))) * 180.0 / Math.PI;
      url=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),false,15);
    }
 else     if (!TextUtils.isEmpty(object.messageOwner.media.title)) {
      int photoWidth=backgroundWidth - AndroidUtilities.dp(21);
      int photoHeight=AndroidUtilities.dp(195);
      url=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),true,15);
    }
 else {
      int photoWidth=backgroundWidth - AndroidUtilities.dp(12);
      int photoHeight=AndroidUtilities.dp(195);
      url=AndroidUtilities.formapMapUrl(currentAccount,lat,lon,(int)(photoWidth / AndroidUtilities.density),(int)(photoHeight / AndroidUtilities.density),true,15);
    }
    return !url.equals(currentUrl);
  }
 else   if (currentPhotoObject == null || currentPhotoObject.location instanceof TLRPC.TL_fileLocationUnavailable) {
    return object.type == 1 || object.type == MessageObject.TYPE_ROUND_VIDEO || object.type == 3 || object.type == 8 || object.isAnyKindOfSticker();
  }
 else   if (currentMessageObject != null && photoNotSet) {
    File cacheFile=FileLoader.getPathToMessage(currentMessageObject.messageOwner);
    return cacheFile.exists();
  }
  return false;
}
