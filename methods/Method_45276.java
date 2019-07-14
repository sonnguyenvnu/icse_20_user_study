public static Resource versionResource(final Resource version){
  return resource(id("version"),DO_NOTHING_APPLIER,new ResourceReader(){
    @Override public MessageContent readFor(    final Request request){
      String text=HttpProtocolVersion.versionOf(version.readFor(request).toString()).text();
      return content(text);
    }
  }
);
}
