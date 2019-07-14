void handleShowUsage(){
  int startOffset=editor.getSelectionStart();
  int stopOffset=editor.getSelectionStop();
  int tabIndex=editor.getSketch().getCurrentCodeIndex();
  pps.whenDoneBlocking(ps -> handleShowUsage(ps,tabIndex,startOffset,stopOffset));
}
