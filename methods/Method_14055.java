public void initializeFromConfig(RangeFacetConfig config,Project project){
  _config=config;
  if (_config._columnName.length() > 0) {
    Column column=project.columnModel.getColumnByName(_config._columnName);
    if (column != null) {
      _cellIndex=column.getCellIndex();
    }
 else {
      _errorMessage="No column named " + _config._columnName;
    }
  }
 else {
    _cellIndex=-1;
  }
  try {
    _eval=MetaParser.parse(_config._expression);
  }
 catch (  ParsingException e) {
    _errorMessage=e.getMessage();
  }
}
