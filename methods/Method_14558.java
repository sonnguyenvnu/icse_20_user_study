@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Blank down " + cellChanges.size() + " cells in column " + column.getName();
}
