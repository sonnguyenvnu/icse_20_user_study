@Override public boolean visit(Project project,Record record){
  hasError=false;
  hasBlank=false;
  Properties bindings=ExpressionUtils.createBindings(project);
  for (int r=record.fromRowIndex; r < record.toRowIndex; r++) {
    Row row=project.rows.get(r);
    visitRow(project,r,row,bindings,record.recordIndex);
  }
  if (hasError) {
    errorCount++;
  }
  if (hasBlank) {
    blankCount++;
  }
  return false;
}
