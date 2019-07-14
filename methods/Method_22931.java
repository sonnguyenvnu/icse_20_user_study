static public void setEditor(Editor editor){
  if (current != null) {
    current.stopTimer();
  }
  editor.console.setCurrent();
}
