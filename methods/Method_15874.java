public String buildUpdateFields(String resultMapId,String tableName,UpdateParam param){
  Pager.reset();
  param.excludes("id");
  RDBTableMetaData tableMetaData=createMeta(tableName,resultMapId);
  RDBDatabaseMetaData databaseMetaDate=getActiveDatabase();
  Dialect dialect=databaseMetaDate.getDialect();
  CommonSqlRender render=(CommonSqlRender)databaseMetaDate.getRenderer(SqlRender.TYPE.SELECT);
  List<CommonSqlRender.OperationColumn> columns=render.parseOperationField(tableMetaData,param);
  SqlAppender appender=new SqlAppender();
  columns.forEach(column -> {
    RDBColumnMetaData columnMetaData=column.getRDBColumnMetaData();
    if (columnMetaData == null) {
      return;
    }
    if (columnMetaData.getName().contains(".")) {
      return;
    }
    Object value;
    try {
      value=propertyUtils.getProperty(param.getData(),columnMetaData.getAlias());
      if (value == null) {
        return;
      }
    }
 catch (    Exception e) {
      return;
    }
    if (value instanceof Sql) {
      appender.add(",",encodeColumn(dialect,columnMetaData.getName()),"=",((Sql)value).getSql());
    }
 else {
      String typeHandler=columnMetaData.getProperty("typeHandler").getValue();
      appender.add(",",encodeColumn(dialect,columnMetaData.getName()),"=","#{data.",columnMetaData.getAlias(),",javaType=",EasyOrmSqlBuilder.getJavaType(columnMetaData.getJavaType()),",jdbcType=",columnMetaData.getJdbcType(),typeHandler != null ? ",typeHandler=" + typeHandler : "","}");
    }
  }
);
  if (!appender.isEmpty()) {
    appender.removeFirst();
  }
 else {
    throw new UnsupportedOperationException("??????");
  }
  return appender.toString();
}
