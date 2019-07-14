public void initializeFromConfig(ListFacetConfig config,Project project){
  _config=config;
  if (_config.columnName.length() > 0) {
    Column column=project.columnModel.getColumnByName(_config.columnName);
    if (column != null) {
      _cellIndex=column.getCellIndex();
    }
 else {
      _errorMessage="No column named " + _config.columnName;
    }
  }
 else {
    _cellIndex=-1;
  }
  try {
    _eval=MetaParser.parse(_config.expression);
  }
 catch (  ParsingException e) {
    _errorMessage=e.getMessage();
  }
}
