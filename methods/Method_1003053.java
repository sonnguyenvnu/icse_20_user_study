@Override public Expression optimize(ExpressionColumn expressionColumn,Column column){
  return expressions[column.getColumnId()];
}
