public static ImageLocation getForDocument(TLRPC.PhotoSize photoSize,TLRPC.Document document){
  if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
    ImageLocation imageLocation=new ImageLocation();
    imageLocation.photoSize=photoSize;
    return imageLocation;
  }
 else   if (photoSize == null || document == null) {
    return null;
  }
  return getForPhoto(photoSize.location,photoSize.size,null,document,null,false,document.dc_id,null,photoSize.type);
}
