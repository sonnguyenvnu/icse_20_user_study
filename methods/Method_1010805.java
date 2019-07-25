@Override public boolean restore(EditorContext editorContext){
  for (  RestorableSelection selection : myRestorableSelections) {
    boolean result=selection.restore(editorContext);
    if (result) {
      return true;
    }
  }
  return false;
}
