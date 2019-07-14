private Row buildNewRow(List<Column> unchangedColumns,Row oldRow,int size){
  Row reusableRow=new Row(size);
  for (int c=0; c < unchangedColumns.size(); c++) {
    Column unchangedColumn=unchangedColumns.get(c);
    int cellIndex=unchangedColumn.getCellIndex();
    reusableRow.setCell(cellIndex,oldRow.getCell(cellIndex));
  }
  return reusableRow;
}
