public void inject(final ActionRequest actionRequest,final Targets targets){
  final ActionRuntime actionRuntime=actionRequest.getActionRuntime();
  final RouteChunk routeChunk=actionRuntime.getRouteChunk();
  if (!routeChunk.hasMacrosOnPath()) {
    return;
  }
  final String[] actionPath=actionRequest.getActionPathChunks();
  int ndx=actionPath.length - 1;
  RouteChunk chunk=routeChunk;
  while (chunk.parent() != null) {
    final PathMacros pathMacros=chunk.pathMacros();
    if (pathMacros != null) {
      injectMacros(actionPath[ndx],pathMacros,targets);
    }
    ndx--;
    chunk=chunk.parent();
  }
}
