boolean relayout(){
  EditorCell cell=getCell();
  if (cell == null) {
    myIsHidden=true;
    return true;
  }
  if (cell instanceof EditorCell_Collection) {
    EditorCell_Collection collectionCell=(EditorCell_Collection)cell;
    myIsHidden=CellTraversalUtil.getFoldedParent(collectionCell) != null;
    if (!myIsHidden) {
      myIsFolded=collectionCell.isCollapsed();
      EditorCell firstLeafCell=CellTraversalUtil.getFirstLeaf(collectionCell);
      myY1=firstLeafCell.getBaseline() - HEIGHT;
      EditorCell lastLeafCell=CellTraversalUtil.getLastLeaf(collectionCell);
      myY2=CellTraversalUtil.getLastLeaf(collectionCell).getBaseline();
      if (!myIsFolded && myY2 - myY1 < 2 * HEIGHT) {
        myIsHidden=true;
      }
    }
    return true;
  }
  return false;
}
