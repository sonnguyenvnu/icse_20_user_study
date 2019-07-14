public RDBDatabaseMetaData getActiveDatabase(){
  DatabaseType type=DataSourceHolder.currentDatabaseType();
switch (type) {
case mysql:
    return mysql;
case oracle:
  return oracle;
case postgresql:
return postgresql;
case h2:
return h2;
case jtds_sqlserver:
case sqlserver:
return mssql;
default :
log.warn("?????????:[{}]",type);
return h2;
}
}
