@Override public void execute(EditorContext context){
  EditorCell selectedCell=context.getSelectedCell();
  SNode node=selectedCell.getSNode();
  SideTransformInfoUtil.removeTransformInfo(node);
  String anchorCellId=selectedCell.getCellId();
  if (mySide == Side.LEFT) {
    SideTransformInfoUtil.addLeftTransformInfo(node,anchorCellId);
  }
 else {
    SideTransformInfoUtil.addRightTransformInfo(node,anchorCellId);
  }
  context.flushEvents();
  EditorCell_STHint sideTransformHintCell=EditorCell_STHint.getSTHintCell(node,context.getEditorComponent());
  assert sideTransformHintCell != null : "STHint cell was not created. Node: " + node + " (concept: " + node.getConcept().getQualifiedName() + " )" + ", anchorCellID: " + anchorCellId;
  sideTransformHintCell.setSubstituteInfo(getSubstituteInfo(selectedCell));
  context.getEditorComponent().changeSelection(sideTransformHintCell);
}
