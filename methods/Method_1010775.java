void activate(int x,int y){
  EditorCell cell=getCell();
  if (cell instanceof EditorCell_Collection) {
    EditorCell_Collection collection=(EditorCell_Collection)cell;
    if (CellTraversalUtil.getFoldedParent(collection) != null) {
      return;
    }
    if (collection.isCollapsed()) {
      collection.unfold();
    }
 else {
      if (isOnBottomButton(y)) {
        JScrollBar verticalScrollBar=((jetbrains.mps.nodeEditor.EditorComponent)myEditor).getVerticalScrollBar();
        verticalScrollBar.setValue(Math.max(verticalScrollBar.getValue() - (myY2 - myY1 - HEIGHT),0));
      }
      collection.fold();
    }
  }
}
