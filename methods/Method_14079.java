@Override public boolean visit(Project project,int rowIndex,Row row){
  hasError=false;
  hasBlank=false;
  Properties bindings=ExpressionUtils.createBindings(project);
  visitRow(project,rowIndex,row,bindings,rowIndex);
  if (hasError) {
    errorCount++;
  }
  if (hasBlank) {
    blankCount++;
  }
  return false;
}
