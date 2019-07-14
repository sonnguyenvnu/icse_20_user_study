public void registerColumnDataForTableRef(final String tableRef,final String tableName){
  if (columnData == null) {
    columnData=new NamedValuesHashMap<>();
  }
  columnData.put(tableRef,new ColumnData(tableName));
}
