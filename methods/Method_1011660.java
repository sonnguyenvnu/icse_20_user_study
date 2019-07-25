public RestorableSelection save(){
  if (mySelectedCell == null || mySelectedCell instanceof EditorCell_Collection) {
    return null;
  }
  EditorCell_Collection parent=mySelectedCell.getParent();
  if (parent.getCellsCount() < EXPECTED_CHILD_INDEX + 1 || IterableUtil.get(parent,EXPECTED_CHILD_INDEX) != mySelectedCell) {
    return null;
  }
  return new RestorableSelectionByCell(new ChildCellLocator(new CellIdLocator(parent.getCellId(),parent.getSNode()),EXPECTED_CHILD_INDEX),createCellSelector());
}
