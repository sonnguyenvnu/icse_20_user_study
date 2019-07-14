protected String createColumnName(RDBColumnMetaData column,String tableAlias){
  if (StringUtils.isEmpty(tableAlias)) {
    tableAlias=column.getTableMetaData().getAlias();
  }
  return column.getTableMetaData().getDatabaseMetaData().getDialect().buildColumnName(tableAlias,column.getName());
}
