@PostConstruct public void init(){
  Objects.requireNonNull(sqlExecutor);
  databaseMetaSuppliers.put(DatabaseType.oracle,() -> {
    OracleRDBDatabaseMetaData metaData=new OracleRDBDatabaseMetaData();
    metaData.setParser(new OracleTableMetaParser(sqlExecutor));
    return metaData;
  }
);
  databaseMetaSuppliers.put(DatabaseType.mysql,() -> {
    MysqlRDBDatabaseMetaData metaData=new MysqlRDBDatabaseMetaData();
    metaData.setParser(new MysqlTableMetaParser(sqlExecutor));
    return metaData;
  }
);
  databaseMetaSuppliers.put(DatabaseType.h2,() -> {
    H2RDBDatabaseMetaData metaData=new H2RDBDatabaseMetaData();
    metaData.setParser(new H2TableMetaParser(sqlExecutor));
    return metaData;
  }
);
  databaseMetaSuppliers.put(DatabaseType.jtds_sqlserver,() -> {
    MSSQLRDBDatabaseMetaData metaData=new MSSQLRDBDatabaseMetaData();
    metaData.setParser(new SqlServer2012TableMetaParser(sqlExecutor));
    return metaData;
  }
);
  databaseMetaSuppliers.put(DatabaseType.sqlserver,databaseMetaSuppliers.get(DatabaseType.jtds_sqlserver));
  databaseMetaSuppliers.put(DatabaseType.postgresql,() -> {
    PGRDBDatabaseMetaData metaData=new PGRDBDatabaseMetaData();
    metaData.setParser(new PGSqlTableMetaParser(sqlExecutor));
    return metaData;
  }
);
}
