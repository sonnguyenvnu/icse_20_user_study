void handleInspect(){
  int off=editor.getSelectionStart();
  int tabIndex=editor.getSketch().getCurrentCodeIndex();
  pps.whenDoneBlocking(ps -> handleInspect(ps,tabIndex,off));
}
