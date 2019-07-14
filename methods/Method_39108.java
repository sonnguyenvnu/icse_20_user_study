public ActionRuntime lookup(final String method,final String[] pathChunks){
  while (true) {
    final ActionRuntime actionRuntime=_lookup(method,pathChunks);
    if (actionRuntime != null) {
      return actionRuntime;
    }
    if (actionsManager.isStrictRoutePaths()) {
      return null;
    }
    final String lastPath=pathChunks[pathChunks.length - 1];
    final int lastNdx=lastPath.lastIndexOf('.');
    if (lastNdx == -1) {
      return null;
    }
    final String pathExtension=lastPath.substring(lastNdx + 1);
    if (StringUtil.equalsOne(pathExtension,actionsManager.getPathExtensionsToStrip()) == -1) {
      return null;
    }
    pathChunks[pathChunks.length - 1]=lastPath.substring(0,lastNdx);
  }
}
