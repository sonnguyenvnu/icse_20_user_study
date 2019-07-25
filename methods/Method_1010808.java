public EditorCell uninstall(){
  assert myInstalled;
  if (myAnchorCell == myBigCell) {
    myBigCell.setBig(true);
  }
 else {
    this.getParent().removeCell(this);
  }
  return myBigCell;
}
