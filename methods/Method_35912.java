public String getBodyAsString(){
  return Strings.stringFromBytes(getBody(),headers.getContentTypeHeader().charset());
}
