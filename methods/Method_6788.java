public boolean hasPhotoStickers(){
  return messageOwner.media != null && messageOwner.media.photo != null && messageOwner.media.photo.has_stickers;
}
