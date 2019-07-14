@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Fill down " + cellChanges.size() + " cells in column " + column.getName();
}
