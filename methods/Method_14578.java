@Override protected String getBriefDescription(Project project){
  return "Create column " + _newColumnName + " at index " + _columnInsertIndex + " by fetching URLs based on column " + _baseColumnName + " using expression " + _urlExpression;
}
