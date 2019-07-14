@Override protected String getBriefDescription(Project project){
  return "Create column " + _newColumnName + " at index " + _columnInsertIndex + " based on column " + _baseColumnName + " using expression " + _expression;
}
