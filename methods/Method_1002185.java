public static boolean add(Runnable runnable){
  if (sInitDone) {
    return false;
  }
  if (Browser.getEngineType() != EngineType.XWalk) {
    return false;
  }
  new XWalkInitHandler(runnable);
  return true;
}
