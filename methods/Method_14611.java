@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Match each of " + cellChanges.size() + " cells to its best candidate in column " + column.getName();
}
