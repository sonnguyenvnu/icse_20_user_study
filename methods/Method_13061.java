public static URI createURI(API api,Collection<Future<Indexes>> collectionOfFutureIndexes,Container.Entry entry,String query,String fragment){
  URI uri=entry.getUri();
  try {
    String path=uri.getPath();
    TypeFactory typeFactory=TypeFactoryService.getInstance().get(entry);
    if (typeFactory != null) {
      Type type=typeFactory.make(api,entry,fragment);
      if (type != null) {
        path=getOuterPath(collectionOfFutureIndexes,entry,type);
      }
    }
    return new URI(uri.getScheme(),uri.getHost(),path,query,fragment);
  }
 catch (  URISyntaxException e) {
    assert ExceptionUtil.printStackTrace(e);
    return uri;
  }
}
