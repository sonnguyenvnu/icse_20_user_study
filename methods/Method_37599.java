private String getMimeSubtype(final String ContentType){
  int start=ContentType.indexOf('/');
  if (start == -1) {
    return ContentType;
  }
  start++;
  return ContentType.substring(start);
}
