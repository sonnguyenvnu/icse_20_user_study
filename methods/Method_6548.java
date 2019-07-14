public static ImageLocation getForLocal(TLRPC.FileLocation location){
  if (location == null) {
    return null;
  }
  ImageLocation imageLocation=new ImageLocation();
  imageLocation.location=new TLRPC.TL_fileLocationToBeDeprecated();
  imageLocation.location.local_id=location.local_id;
  imageLocation.location.volume_id=location.volume_id;
  imageLocation.location.secret=location.secret;
  imageLocation.location.dc_id=location.dc_id;
  return imageLocation;
}
