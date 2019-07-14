public String getKey(Object parentObject,Object fullObject){
  if (secureDocument != null) {
    return secureDocument.secureFile.dc_id + "_" + secureDocument.secureFile.id;
  }
 else   if (photoSize instanceof TLRPC.TL_photoStrippedSize) {
    if (photoSize.bytes.length > 0) {
      return getStippedKey(parentObject,fullObject,photoSize);
    }
  }
 else   if (location != null) {
    return location.volume_id + "_" + location.local_id;
  }
 else   if (webFile != null) {
    return Utilities.MD5(webFile.url);
  }
 else   if (document != null) {
    if (document.id != 0 && document.dc_id != 0) {
      return document.dc_id + "_" + document.id;
    }
  }
 else   if (path != null) {
    return Utilities.MD5(path);
  }
  return null;
}
