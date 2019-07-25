protected void select(EditorContext context,SNode node){
  EditorComponent editorComponent=context.getEditorComponent();
  EditorCell cell=editorComponent.findNodeCell(node);
  if (cell != null) {
    EditorCell errorCell=CellFinderUtil.findFirstError(cell,true);
    if (errorCell != null) {
      ((jetbrains.mps.nodeEditor.EditorComponent)editorComponent).changeSelectionWRTFocusPolicy(errorCell);
    }
 else {
      ((jetbrains.mps.nodeEditor.EditorComponent)editorComponent).changeSelectionWRTFocusPolicy(cell);
    }
  }
}
