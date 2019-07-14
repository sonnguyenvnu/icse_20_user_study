protected String createDescription(Column column,List<CellAtRow> cellsAtRows){
  return "Create new column " + _newColumnName + ", filling " + cellsAtRows.size() + " rows by fetching URLs based on column " + column.getName() + " and formulated as " + _urlExpression;
}
