@Override public boolean filterRow(Project project,int rowIndex,Row row){
  Cell x_cell=_x_cellIndex < 0 ? null : row.getCell(_x_cellIndex);
  Properties x_bindings=ExpressionUtils.createBindings(project);
  ExpressionUtils.bind(x_bindings,row,rowIndex,_x_columnName,x_cell);
  Object x_value=_x_evaluable.evaluate(x_bindings);
  Cell y_cell=_y_cellIndex < 0 ? null : row.getCell(_y_cellIndex);
  Properties y_bindings=ExpressionUtils.createBindings(project);
  ExpressionUtils.bind(y_bindings,row,rowIndex,_y_columnName,y_cell);
  Object y_value=_y_evaluable.evaluate(y_bindings);
  if (x_value != null && y_value != null) {
    if (x_value.getClass().isArray() || y_value.getClass().isArray()) {
      return false;
    }
 else     if (x_value instanceof Collection<?> || y_value instanceof Collection<?>) {
      return false;
    }
  }
  return checkValue(x_value,y_value);
}
