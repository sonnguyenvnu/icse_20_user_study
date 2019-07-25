@Override public void execute(@NotNull String pattern){
  SNodeAccessUtil.setReferenceTarget(myNode,myLink,myTargetNode);
  myEditorContext.flushEvents();
  EditorCell selectedCell=myEditorContext.getSelectedCell();
  if (selectedCell instanceof EditorCell_Label && ((EditorCell_Label)selectedCell).isEditable()) {
    EditorCell_Label cell=(EditorCell_Label)selectedCell;
    cell.end();
  }
}
