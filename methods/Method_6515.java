public BitmapDrawable getImageFromMemory(TLObject fileLocation,String httpUrl,String filter){
  if (fileLocation == null && httpUrl == null) {
    return null;
  }
  String key=null;
  if (httpUrl != null) {
    key=Utilities.MD5(httpUrl);
  }
 else {
    if (fileLocation instanceof TLRPC.FileLocation) {
      TLRPC.FileLocation location=(TLRPC.FileLocation)fileLocation;
      key=location.volume_id + "_" + location.local_id;
    }
 else     if (fileLocation instanceof TLRPC.Document) {
      TLRPC.Document location=(TLRPC.Document)fileLocation;
      key=location.dc_id + "_" + location.id;
    }
 else     if (fileLocation instanceof SecureDocument) {
      SecureDocument location=(SecureDocument)fileLocation;
      key=location.secureFile.dc_id + "_" + location.secureFile.id;
    }
 else     if (fileLocation instanceof WebFile) {
      WebFile location=(WebFile)fileLocation;
      key=Utilities.MD5(location.url);
    }
  }
  if (filter != null) {
    key+="@" + filter;
  }
  return memCache.get(key);
}
