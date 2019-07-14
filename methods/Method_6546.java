public static ImageLocation getForSticker(TLRPC.PhotoSize photoSize,TLRPC.Document sticker){
  if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
    ImageLocation imageLocation=new ImageLocation();
    imageLocation.photoSize=photoSize;
    return imageLocation;
  }
 else   if (photoSize == null || sticker == null) {
    return null;
  }
  TLRPC.InputStickerSet stickerSet=DataQuery.getInputStickerSet(sticker);
  if (stickerSet == null) {
    return null;
  }
  return getForPhoto(photoSize.location,photoSize.size,null,null,null,false,sticker.dc_id,stickerSet,photoSize.type);
}
