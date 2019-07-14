public String buildSelectFields(String resultMapId,String tableName,Object arg){
  QueryParam param=null;
  if (arg instanceof QueryParam) {
    param=((QueryParam)arg);
  }
  if (param == null) {
    return "*";
  }
  if (param.isPaging() && Pager.get() == null) {
    Pager.doPaging(param.getPageIndex(),param.getPageSize());
  }
 else {
    Pager.reset();
  }
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
    String cname=columnMetaData.getName();
    if (!cname.contains(".")) {
      cname=tableMetaData.getName().concat(".").concat(cname);
    }
    boolean isJpa=columnMetaData.getProperty("fromJpa",false).isTrue();
    appender.add(",",encodeColumn(dialect,cname)," AS ",dialect.getQuoteStart(),isJpa ? columnMetaData.getAlias() : columnMetaData.getName(),dialect.getQuoteEnd());
  }
);
  param.getIncludes().remove("*");
  if (appender.isEmpty()) {
    return "*";
  }
  appender.removeFirst();
  return appender.toString();
}
