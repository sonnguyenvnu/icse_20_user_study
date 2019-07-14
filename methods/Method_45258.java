@Override public MediaType getContentType(final HttpRequest request){
  return MediaType.create("application","json").withCharset(Charset.defaultCharset());
}
