public static Resource versionResource(final HttpProtocolVersion version){
  return resource(id("version"),DO_NOTHING_APPLIER,new ResourceReader(){
    @Override public MessageContent readFor(    final Request request){
      return content(version.text());
    }
  }
);
}
