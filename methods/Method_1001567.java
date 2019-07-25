public static Graphviz create(ISkinParam skinParam,String dotString,String... type){
  if (useVizJs(skinParam)) {
    Log.info("Using " + VIZJS);
    return new GraphvizJs(dotString);
  }
  final AbstractGraphviz result;
  if (isWindows()) {
    result=new GraphvizWindows(skinParam,dotString,type);
  }
 else {
    result=new GraphvizLinux(skinParam,dotString,type);
  }
  if (result.getExeState() != ExeState.OK && VizJsEngine.isOk()) {
    Log.info("Error with file " + result.getDotExe() + ": " + result.getExeState().getTextMessage());
    Log.info("Using " + VIZJS);
    return new GraphvizJs(dotString);
  }
  return result;
}
