@Override public RowFilter getRowFilter(Project project){
  if (_eval != null && _errorMessage == null && _config.isSelected()) {
    return new ExpressionTimeComparisonRowFilter(getRowEvaluable(project),_config._selectTime,_config._selectNonTime,_config._selectBlank,_config._selectError){
      @Override protected boolean checkValue(      long t){
        return t >= _config._from && t <= _config._to;
      }
    }
;
  }
 else {
    return null;
  }
}
