private byte[] getFileReference(TLRPC.Photo photo,TLRPC.InputFileLocation location,boolean[] needReplacement,TLRPC.InputFileLocation[] replacement){
  if (photo == null) {
    return null;
  }
  if (location instanceof TLRPC.TL_inputPhotoFileLocation) {
    return photo.id == location.id ? photo.file_reference : null;
  }
 else   if (location instanceof TLRPC.TL_inputFileLocation) {
    for (int a=0, size=photo.sizes.size(); a < size; a++) {
      TLRPC.PhotoSize photoSize=photo.sizes.get(a);
      byte[] result=getFileReference(photoSize,location,needReplacement);
      if (needReplacement != null && needReplacement[0]) {
        replacement[0]=new TLRPC.TL_inputPhotoFileLocation();
        replacement[0].id=photo.id;
        replacement[0].volume_id=location.volume_id;
        replacement[0].local_id=location.local_id;
        replacement[0].access_hash=photo.access_hash;
        replacement[0].file_reference=photo.file_reference;
        replacement[0].thumb_size=photoSize.type;
        return photo.file_reference;
      }
      if (result != null) {
        return result;
      }
    }
  }
  return null;
}
