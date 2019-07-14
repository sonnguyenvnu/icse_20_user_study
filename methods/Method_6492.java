private byte[] getFileReference(TLRPC.Document document,TLRPC.InputFileLocation location,boolean[] needReplacement,TLRPC.InputFileLocation[] replacement){
  if (document == null || location == null) {
    return null;
  }
  if (location instanceof TLRPC.TL_inputDocumentFileLocation) {
    if (document.id == location.id) {
      return document.file_reference;
    }
  }
 else {
    for (int a=0, size=document.thumbs.size(); a < size; a++) {
      TLRPC.PhotoSize photoSize=document.thumbs.get(a);
      byte[] result=getFileReference(photoSize,location,needReplacement);
      if (needReplacement != null && needReplacement[0]) {
        replacement[0]=new TLRPC.TL_inputDocumentFileLocation();
        replacement[0].id=document.id;
        replacement[0].volume_id=location.volume_id;
        replacement[0].local_id=location.local_id;
        replacement[0].access_hash=document.access_hash;
        replacement[0].file_reference=document.file_reference;
        replacement[0].thumb_size=photoSize.type;
        return document.file_reference;
      }
      if (result != null) {
        return result;
      }
    }
  }
  return null;
}
