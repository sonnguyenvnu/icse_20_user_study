protected String createDescription(Column column,List<CellAtRow> cellsAtRows){
  return "Create new column " + _newColumnName + " based on column " + column.getName() + " by filling " + cellsAtRows.size() + " rows with " + _expression;
}
