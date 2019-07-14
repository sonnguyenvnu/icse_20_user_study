protected String createDescription(Column column,List<CellAtRow> cellsAtRows){
  return "Extend data at index " + _columnInsertIndex + " based on column " + column.getName() + " by filling " + cellsAtRows.size();
}
