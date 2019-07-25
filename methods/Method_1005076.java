@Override public List<LazyElementCell> process(final List<LazyElementCell> elementCells){
  elementCells.removeIf(elementCell -> !elementCell.isDeleted() && !test(elementCell));
  return elementCells;
}
