public EditorCell install(){
  assert !myInstalled;
  myInstalled=true;
  if (myAnchorCell == myBigCell) {
    jetbrains.mps.nodeEditor.cells.EditorCell_Collection wrapperCell=jetbrains.mps.nodeEditor.cells.EditorCell_Collection.createHorizontal(getContext(),getSNode());
    wrapperCell.setSelectable(false);
    wrapperCell.setBig(true);
    EditorCellContext cellContext=myBigCell.getCellContext();
    assert cellContext != null;
    wrapperCell.setCellContext(cellContext);
    wrapperCell.setCanBeSynchronized(myBigCell instanceof SynchronizeableEditorCell && ((SynchronizeableEditorCell)myBigCell).canBeSynchronized());
    myBigCell.setBig(false);
    if (!isRight()) {
      wrapperCell.addEditorCell(this);
    }
    wrapperCell.addEditorCell(myBigCell);
    if (isRight()) {
      wrapperCell.addEditorCell(this);
    }
    return wrapperCell;
  }
  EditorCell_Collection cellCollection=myAnchorCell.getParent();
  if (isRight()) {
    cellCollection.addEditorCellAfter(this,myAnchorCell);
  }
 else {
    cellCollection.addEditorCellBefore(this,myAnchorCell);
  }
  return myBigCell;
}
