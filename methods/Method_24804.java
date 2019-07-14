void handleInspect(MouseEvent evt){
  int off=editor.getJavaTextArea().xyToOffset(evt.getX(),evt.getY());
  if (off < 0)   return;
  int tabIndex=editor.getSketch().getCurrentCodeIndex();
  pps.whenDoneBlocking(ps -> handleInspect(ps,tabIndex,off));
}
