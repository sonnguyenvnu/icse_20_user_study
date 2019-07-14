private ActionRuntime _lookup(String method,final String[] pathChunks){
  if (method != null) {
    method=method.toUpperCase();
    final RouteChunk methodChunk=root.findOrCreateChild(method);
    final ActionRuntime actionRuntime=lookupFrom(methodChunk,pathChunks);
    if (actionRuntime != null) {
      return actionRuntime;
    }
  }
  if (anyMethodChunk != null) {
    final ActionRuntime actionRuntime=lookupFrom(anyMethodChunk,pathChunks);
    if (actionRuntime != null) {
      return actionRuntime;
    }
  }
  return null;
}
