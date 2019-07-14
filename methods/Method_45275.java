public static Resource methodResource(final String method){
  return resource(id("method"),DO_NOTHING_APPLIER,new ResourceReader(){
    @Override public MessageContent readFor(    final Request request){
      return content(method.toUpperCase());
    }
  }
);
}
