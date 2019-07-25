public boolean relayout(){
  EditorCell cell1=myCellInfo.findCell(myEditor);
  EditorCell cell2=mySecondCellInfo.findCell(myEditor);
  if (cell1 != null && cell2 != null) {
    if (cell1.getY() <= cell2.getY()) {
      setY1(cell1.getY());
      setY2(cell2.getY() + cell2.getHeight());
    }
 else {
      setY1(cell2.getY());
      setY2(cell1.getY() + cell1.getHeight());
    }
    return true;
  }
 else {
    return false;
  }
}
