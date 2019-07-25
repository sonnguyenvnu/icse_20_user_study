public void highlight(EditorCell cell,EditorCell cell2,Color c){
  assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.highlight() should be called in eventDispatchThread";
  assert cell.getEditorComponent() == myEditorComponent : "cell must be from my editor";
  myBracketsPainter.addBracket(cell,cell2,c);
  relayout(true);
  repaint();
}
