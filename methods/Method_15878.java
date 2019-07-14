public String buildOrder(String resultMapId,String tableName,Object arg){
  QueryParam param=null;
  if (arg instanceof QueryParam) {
    param=((QueryParam)arg);
  }
  if (param == null) {
    return "";
  }
  RDBTableMetaData tableMetaData=createMeta(tableName,resultMapId);
  SqlAppender appender=new SqlAppender(" order by ");
  param.getSorts().forEach(sort -> {
    RDBColumnMetaData column=tableMetaData.getColumn(sort.getName());
    if (column == null) {
      column=tableMetaData.findColumn(sort.getName());
    }
    if (column == null) {
      return;
    }
    String cname=column.getName();
    if (!cname.contains(".")) {
      cname=tableMetaData.getName().concat(".").concat(cname);
    }
    appender.add(encodeColumn(tableMetaData.getDatabaseMetaData().getDialect(),cname)," ",sort.getOrder(),",");
  }
);
  if (appender.isEmpty()) {
    return "";
  }
  appender.removeLast();
  return appender.toString();
}
