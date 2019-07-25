public void execute(EditorContext editorContext){
  RestorableSelection restorableSelection=createRestorableSelection(editorContext);
  executeInternal(editorContext);
  editorContext.flushEvents();
  check_j6szs9_a3a1(restorableSelection,editorContext);
}
