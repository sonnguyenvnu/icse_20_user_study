@Override public void select(@NotNull SNode createdNode,@NotNull String pattern){
  EditorComponent editorComponent=((EditorComponent)myEditorContext.getEditorComponent());
  EditorCell cell=editorComponent.findNodeCell(createdNode);
  if (cell != null) {
    EditorCell errorCell=CellFinderUtil.findFirstError(cell,true);
    if (errorCell != null) {
      editorComponent.changeSelectionWRTFocusPolicy(errorCell);
    }
 else {
      editorComponent.changeSelectionWRTFocusPolicy(cell);
    }
  }
}
