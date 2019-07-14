public static int getTypeOfFile(String path,boolean isDirectory){
  String mimeType=MimeTypes.getMimeType(path,isDirectory);
  if (mimeType == null)   return NOT_KNOWN;
  Integer type=sMimeIconIds.get(mimeType);
  if (type != null)   return type;
 else {
    if (checkType(mimeType,"text"))     return TEXT;
 else     if (checkType(mimeType,"image"))     return IMAGE;
 else     if (checkType(mimeType,"video"))     return VIDEO;
 else     if (checkType(mimeType,"audio"))     return AUDIO;
 else     if (checkType(mimeType,"crypt"))     return ENCRYPTED;
 else     return NOT_KNOWN;
  }
}
