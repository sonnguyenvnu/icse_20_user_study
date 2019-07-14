private static ImageLocation getForPhoto(TLRPC.FileLocation location,int size,TLRPC.Photo photo,TLRPC.Document document,TLRPC.InputPeer photoPeer,boolean photoPeerBig,int dc_id,TLRPC.InputStickerSet stickerSet,String thumbSize){
  if (location == null || photo == null && photoPeer == null && stickerSet == null && document == null) {
    return null;
  }
  ImageLocation imageLocation=new ImageLocation();
  imageLocation.dc_id=dc_id;
  imageLocation.photo=photo;
  imageLocation.currentSize=size;
  imageLocation.photoPeer=photoPeer;
  imageLocation.photoPeerBig=photoPeerBig;
  imageLocation.stickerSet=stickerSet;
  if (location instanceof TLRPC.TL_fileLocationToBeDeprecated) {
    imageLocation.location=(TLRPC.TL_fileLocationToBeDeprecated)location;
    if (photo != null) {
      imageLocation.file_reference=photo.file_reference;
      imageLocation.access_hash=photo.access_hash;
      imageLocation.photoId=photo.id;
      imageLocation.thumbSize=thumbSize;
    }
 else     if (document != null) {
      imageLocation.file_reference=document.file_reference;
      imageLocation.access_hash=document.access_hash;
      imageLocation.documentId=document.id;
      imageLocation.thumbSize=thumbSize;
    }
  }
 else {
    imageLocation.location=new TLRPC.TL_fileLocationToBeDeprecated();
    imageLocation.location.local_id=location.local_id;
    imageLocation.location.volume_id=location.volume_id;
    imageLocation.location.secret=location.secret;
    imageLocation.dc_id=location.dc_id;
    imageLocation.file_reference=location.file_reference;
    imageLocation.key=location.key;
    imageLocation.iv=location.iv;
    imageLocation.access_hash=location.secret;
  }
  return imageLocation;
}
