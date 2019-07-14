public RouteChunk registerPath(String method,String path){
  if (method == null) {
    method=ANY_METHOD;
  }
 else {
    method=method.toUpperCase();
  }
  RouteChunk chunk=root.findOrCreateChild(method);
  if (method.equals(ANY_METHOD)) {
    anyMethodChunk=chunk;
  }
  path=StringUtil.cutSurrounding(path,StringPool.SLASH);
  String[] pathChunks=StringUtil.splitc(path,'/');
  for (  String pathChunk : pathChunks) {
    chunk=chunk.findOrCreateChild(pathChunk);
  }
  return chunk;
}
