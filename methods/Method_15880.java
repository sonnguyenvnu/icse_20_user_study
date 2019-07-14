public String buildWhere(String resultMapId,String tableName,List<Term> terms){
  RDBTableMetaData tableMetaData=createMeta(tableName,resultMapId);
  RDBDatabaseMetaData databaseMetaDate=getActiveDatabase();
  SimpleWhereSqlBuilder builder=new SimpleWhereSqlBuilder(){
    @Override public Dialect getDialect(){
      return databaseMetaDate.getDialect();
    }
  }
;
  SqlAppender appender=new SqlAppender();
  builder.buildWhere(tableMetaData,"",terms,appender,new HashSet<>());
  return appender.toString();
}
