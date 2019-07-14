protected String getBriefDescription(){
  if (_combinedColumnName != null) {
    if (_columnCount > 0) {
      return "Transpose cells in " + _columnCount + " column(s) starting with " + _startColumnName + " into rows in one new column named " + _combinedColumnName;
    }
 else {
      return "Transpose cells in columns starting with " + _startColumnName + " into rows in one new column named " + _combinedColumnName;
    }
  }
 else {
    if (_columnCount > 0) {
      return "Transpose cells in " + _columnCount + " column(s) starting with " + _startColumnName + " into rows in two new columns named " + _keyColumnName + " and " + _valueColumnName;
    }
 else {
      return "Transpose cells in columns starting with " + _startColumnName + " into rows in two new columns named " + _keyColumnName + " and " + _valueColumnName;
    }
  }
}
