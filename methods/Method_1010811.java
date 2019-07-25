public void execute(EditorContext context){
  EditorCell selectedCell=myEditorContext.getSelectedCell();
  NodeRangeSelection newSelection=new NodeRangeSelection(myEditorContext.getEditorComponent(),myNode,myNode,myFilter,myEmptyCellId);
  if (selectedCell != null && selectedCell.isBig()) {
    newSelection=newSelection.enlargeSelection(myNext);
  }
  if (newSelection != null) {
    myEditorContext.getSelectionManager().pushSelection(newSelection);
    newSelection.ensureVisible();
  }
}
