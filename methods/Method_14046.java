@Override public RowFilter getRowFilter(Project project){
  return _eval == null || _errorMessage != null || (_config.selection.size() == 0 && !_config.selectBlank && !_config.selectError) ? null : new ExpressionEqualRowFilter(_eval,_config.columnName,_cellIndex,createMatches(),_config.selectBlank,_config.selectError,_config.invert);
}
