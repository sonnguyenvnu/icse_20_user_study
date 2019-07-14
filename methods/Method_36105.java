@Override public String getBodyAsString(){
  return stringFromBytes(getBody(),encodingFromContentTypeHeaderOrUtf8());
}
