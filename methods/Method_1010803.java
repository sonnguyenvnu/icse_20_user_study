@Override public EditorCell locate(@NotNull EditorContext editorContext){
  return editorContext.getEditorComponent().findCellWithId(myParentNode,myCellId);
}
