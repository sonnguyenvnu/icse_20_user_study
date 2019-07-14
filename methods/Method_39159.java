/** 
 * Dispatches to the template location created from result value and JSP extension. Does its forward via a <code>RequestDispatcher</code>. If the dispatch fails, a 404 error will be sent back in the http response.
 */
@Override public void render(final ActionRequest actionRequest,final Object resultValue) throws Exception {
  final PathResult pathResult;
  if (resultValue == null) {
    pathResult=resultOf(StringPool.EMPTY);
  }
 else {
    if (resultValue instanceof String) {
      pathResult=resultOf(resultValue);
    }
 else {
      pathResult=(PathResult)resultValue;
    }
  }
  final String resultBasePath=actionRequest.getActionRuntime().getResultBasePath();
  final String path=pathResult != null ? pathResult.path() : StringPool.EMPTY;
  final String actionAndResultPath=resultBasePath + (pathResult != null ? ':' + path : StringPool.EMPTY);
  String target=targetCache.get(actionAndResultPath);
  if (target == null) {
    if (log.isDebugEnabled()) {
      log.debug("new target: " + actionAndResultPath);
    }
    target=resolveTarget(actionRequest,path);
    if (target == null) {
      targetNotFound(actionRequest,actionAndResultPath);
      return;
    }
    if (log.isDebugEnabled()) {
      log.debug("target found: " + target);
    }
    targetCache.put(actionAndResultPath,target);
  }
  renderView(actionRequest,target);
}
