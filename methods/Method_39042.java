/** 
 * Registers manually created  {@link ActionRuntime action runtime configurations}. Optionally, if action path with the same name already exist, exception will be thrown.
 */
public ActionRuntime registerActionRuntime(final ActionRuntime actionRuntime){
  final String actionPath=actionRuntime.getActionPath();
  final String method=actionRuntime.getActionMethod();
  log.debug(() -> "Madvoc action: " + ifNotNull(method,m -> m + " ") + actionRuntime.getActionPath() + " => " + actionRuntime.createActionString());
  final RouteChunk routeChunk=routes.registerPath(method,actionPath);
  if (routeChunk.value() != null) {
    if (detectDuplicatePathsEnabled) {
      throw new MadvocException("Duplicate action path for [" + actionRuntime + "] occupied by: [" + routeChunk.value() + "]");
    }
  }
 else {
    actionsCount++;
  }
  routeChunk.bind(actionRuntime);
  runtimes.put(actionRuntime.createActionString(),actionRuntime);
  if (actionRuntime.isAsync()) {
    asyncMode=true;
  }
  return actionRuntime;
}
