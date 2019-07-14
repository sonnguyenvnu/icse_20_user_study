@Override public RowFilter getRowFilter(Project project){
  if (_eval != null && _errorMessage == null && _config._selected) {
    return new ExpressionNumberComparisonRowFilter(getRowEvaluable(project),_config._selectNumeric,_config._selectNonNumeric,_config._selectBlank,_config._selectError){
      @Override protected boolean checkValue(      double d){
        return d >= _config._from && d < _config._to;
      }
    }
;
  }
 else {
    return null;
  }
}
