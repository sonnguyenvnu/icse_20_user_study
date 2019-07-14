private static void focusRequestOnUiThread(LithoHandler mainThreadHandler,Runnable runnable){
  if (isMainThread()) {
    runnable.run();
  }
 else {
    String tag=EMPTY_STRING;
    if (mainThreadHandler.isTracing()) {
      tag="SectionTree.focusRequestOnUiThread";
    }
    mainThreadHandler.post(runnable,tag);
  }
}
