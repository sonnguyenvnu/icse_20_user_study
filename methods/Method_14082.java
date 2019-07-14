protected Object evalRow(Project project,int rowIndex,Row row,Properties bindings){
  Cell cell=_cellIndex < 0 ? null : row.getCell(_cellIndex);
  ExpressionUtils.bind(bindings,row,rowIndex,_columnName,cell);
  return _evaluable.evaluate(bindings);
}
