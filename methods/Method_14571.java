@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  return "Text transform on " + cellChanges.size() + " cells in column " + column.getName() + ": " + _expression;
}
