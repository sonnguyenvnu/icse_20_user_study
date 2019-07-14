private String getMimeType(final String ContentType){
  int pos=ContentType.indexOf('/');
  if (pos == -1) {
    return ContentType;
  }
  return ContentType.substring(1,pos);
}
