@Override public void run(String... args) throws Exception {
  if (!appProperties.isAutoInit()) {
    log.debug("app auto init is disabled");
    return;
  }
  DatabaseType type=DataSourceHolder.currentDatabaseType();
  SystemVersion version=appProperties.build();
  if (version.getName() == null) {
    version.setName("unknown");
  }
  Connection connection=null;
  String jdbcUserName;
  try {
    connection=DataSourceHolder.currentDataSource().getNative().getConnection();
    jdbcUserName=connection.getMetaData().getUserName();
  }
  finally {
    if (null != connection) {
      connection.close();
    }
  }
  RDBDatabaseMetaData metaData;
switch (type) {
case oracle:
    metaData=new OracleRDBDatabaseMetaData();
  metaData.setParser(new OracleTableMetaParser(sqlExecutor));
break;
case postgresql:
metaData=new PGRDBDatabaseMetaData();
metaData.setParser(new PGSqlTableMetaParser(sqlExecutor));
break;
case sqlserver:
case jtds_sqlserver:
metaData=new MSSQLRDBDatabaseMetaData();
metaData.setParser(new SqlServer2012TableMetaParser(sqlExecutor));
break;
case mysql:
String engine=environment.getProperty("mysql.engine");
if (StringUtils.hasText(engine)) {
metaData=new MysqlRDBDatabaseMetaData(engine);
}
 else {
metaData=new MysqlRDBDatabaseMetaData();
}
metaData.setParser(new MysqlTableMetaParser(sqlExecutor));
break;
default :
metaData=new H2RDBDatabaseMetaData();
metaData.setParser(new H2TableMetaParser(sqlExecutor));
break;
}
metaData.init();
SimpleDatabase database=new SimpleDatabase(metaData,sqlExecutor);
database.setAutoParse(true);
SystemInitialize initialize=new SystemInitialize(sqlExecutor,database,version);
initialize.addScriptContext("db",jdbcUserName);
initialize.addScriptContext("dbType",type.name());
initialize.setExcludeTables(appProperties.getInitTableExcludes());
initialize.install();
}
