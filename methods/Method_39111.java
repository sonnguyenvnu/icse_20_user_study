private ActionRuntime match(final RouteChunk chunk,final String[] path,final int ndx){
  final int maxDeep=path.length - 1;
  if (ndx > maxDeep) {
    return null;
  }
  if (!chunk.match(path[ndx])) {
    return null;
  }
  if (ndx == maxDeep) {
    if (chunk.isEndpoint()) {
      return chunk.value();
    }
    return null;
  }
  final RouteChunk[] children=chunk.children();
  if (children == null) {
    return null;
  }
  ActionRuntime matchedRuntime=null;
  for (  final RouteChunk child : children) {
    final ActionRuntime match=match(child,path,ndx + 1);
    if (match != null) {
      if (matchedRuntime == null) {
        matchedRuntime=match;
      }
 else {
        if (matchedRuntime.getRouteChunk().hasMacrosOnPath()) {
          if (!match.getRouteChunk().hasMacrosOnPath()) {
            matchedRuntime=match;
          }
        }
      }
    }
  }
  if (matchedRuntime != null) {
    return matchedRuntime;
  }
  return null;
}
