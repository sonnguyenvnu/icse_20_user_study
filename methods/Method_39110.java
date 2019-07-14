private ActionRuntime lookupFrom(final RouteChunk chunk,final String[] path){
  RouteChunk[] children=chunk.children();
  if (children == null) {
    return null;
  }
  for (  RouteChunk child : children) {
    ActionRuntime matched=match(child,path,0);
    if (matched != null) {
      return matched;
    }
  }
  return null;
}
