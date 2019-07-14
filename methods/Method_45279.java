public static Resource uriResource(final String uri){
  return resource(id(MocoConfig.URI_ID),uriConfigApplier(MocoConfig.URI_ID,uri),new ResourceReader(){
    @Override public MessageContent readFor(    final Request request){
      return content(uri);
    }
  }
);
}
