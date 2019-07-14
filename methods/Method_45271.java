public static ContentResource textResource(final String text){
  return contentResource(id("text"),DO_NOTHING_APPLIER,new ContentResourceReader(){
    @Override public MediaType getContentType(    final HttpRequest request){
      return FileContentType.DEFAULT_CONTENT_TYPE_WITH_CHARSET;
    }
    @Override public MessageContent readFor(    final Request request){
      return content(text);
    }
  }
);
}
