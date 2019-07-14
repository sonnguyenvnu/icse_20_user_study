private byte[] getFileReference(TLRPC.WebPage webpage,TLRPC.InputFileLocation location,boolean[] needReplacement,TLRPC.InputFileLocation[] replacement){
  byte[] result=getFileReference(webpage.document,location,needReplacement,replacement);
  if (result != null) {
    return result;
  }
  result=getFileReference(webpage.photo,location,needReplacement,replacement);
  if (result != null) {
    return result;
  }
  if (result == null && webpage.cached_page != null) {
    for (int b=0, size2=webpage.cached_page.documents.size(); b < size2; b++) {
      result=getFileReference(webpage.cached_page.documents.get(b),location,needReplacement,replacement);
      if (result != null) {
        return result;
      }
    }
    for (int b=0, size2=webpage.cached_page.photos.size(); b < size2; b++) {
      result=getFileReference(webpage.cached_page.photos.get(b),location,needReplacement,replacement);
      if (result != null) {
        return result;
      }
    }
  }
  return null;
}
