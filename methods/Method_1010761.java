@Override public void execute(EditorContext context){
  EditorCell editorCell=context.getSelectedCell();
  EditorCell_Collection targetCell=findCell(editorCell);
  targetCell.fold();
}
