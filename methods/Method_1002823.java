@Override public HttpFile get(String path,Clock clock,@Nullable String contentEncoding,HttpHeaders additionalHeaders){
  RouteUtil.ensureAbsolutePath(path,"path");
  final String resourcePath=rootDir.isEmpty() ? path.substring(1) : rootDir + path;
  final HttpFileBuilder builder=HttpFileBuilder.ofResource(classLoader,resourcePath);
  return FileSystemHttpVfs.build(builder,clock,path,contentEncoding,additionalHeaders);
}
