public String registerColumnDataForColumnCode(final String tableName,final String column){
  if (columnData == null) {
    columnData=new NamedValuesHashMap<>();
  }
  String columnCode=COL_CODE_PREFIX + Integer.toString(columnCount++) + '_';
  columnData.put(columnCode,new ColumnData(tableName,column));
  return columnCode;
}
