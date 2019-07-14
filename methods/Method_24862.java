void handleRename(){
  int startOffset=editor.getSelectionStart();
  int stopOffset=editor.getSelectionStop();
  int tabIndex=editor.getSketch().getCurrentCodeIndex();
  pps.whenDoneBlocking(ps -> handleRename(ps,tabIndex,startOffset,stopOffset));
}
