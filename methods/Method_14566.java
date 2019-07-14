@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Mass edit " + cellChanges.size() + " cells in column " + column.getName();
}
