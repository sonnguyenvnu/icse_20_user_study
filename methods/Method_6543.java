public static ImageLocation getForPhoto(TLRPC.PhotoSize photoSize,TLRPC.Photo photo){
  if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
    ImageLocation imageLocation=new ImageLocation();
    imageLocation.photoSize=photoSize;
    return imageLocation;
  }
 else   if (photoSize == null || photo == null) {
    return null;
  }
  int dc_id;
  if (photo.dc_id != 0) {
    dc_id=photo.dc_id;
  }
 else {
    dc_id=photoSize.location.dc_id;
  }
  return getForPhoto(photoSize.location,photoSize.size,photo,null,null,false,dc_id,null,photoSize.type);
}
