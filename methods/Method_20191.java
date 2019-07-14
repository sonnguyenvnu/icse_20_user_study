/** 
 * If enabled, DEBUG logcat messages will be printed to show when models are rebuilt, the time taken to build them, the time taken to diff them, and the item change outcomes from the differ. The tag of the logcat message is the class name of your EpoxyController. <p> This is useful to verify that models are being diffed as expected, as well as to watch for slowdowns in model building or diffing to indicate when you should optimize model building or model hashCode/equals implementations (which can often slow down diffing). <p> This should only be used in debug builds to avoid a performance hit in prod.
 */
public void setDebugLoggingEnabled(boolean enabled){
  assertNotBuildingModels();
  if (enabled) {
    timer=new DebugTimer(getClass().getSimpleName());
    if (debugObserver == null) {
      debugObserver=new EpoxyDiffLogger(getClass().getSimpleName());
    }
    adapter.registerAdapterDataObserver(debugObserver);
  }
 else {
    timer=NO_OP_TIMER;
    if (debugObserver != null) {
      adapter.unregisterAdapterDataObserver(debugObserver);
    }
  }
}
