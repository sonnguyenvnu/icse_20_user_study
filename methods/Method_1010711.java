@Override public void execute(@NotNull String pattern){
  SNodeAccessUtil.setPropertyValue(myNode,myProperty,myValue);
  myEditorContext.flushEvents();
  jetbrains.mps.openapi.editor.cells.EditorCell selectedCell=myEditorContext.getSelectedCell();
  if (selectedCell instanceof jetbrains.mps.nodeEditor.cells.EditorCell_Label && ((jetbrains.mps.nodeEditor.cells.EditorCell_Label)selectedCell).isEditable()) {
    jetbrains.mps.nodeEditor.cells.EditorCell_Label cell=(jetbrains.mps.nodeEditor.cells.EditorCell_Label)selectedCell;
    cell.end();
  }
}
