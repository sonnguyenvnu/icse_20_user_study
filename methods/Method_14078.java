@Override public Object eval(Project project,int rowIndex,Row row,Properties bindings){
  Cell cell=row.getCell(_cellIndex);
  ExpressionUtils.bind(bindings,row,rowIndex,_columnName,cell);
  return _eval.evaluate(bindings);
}
