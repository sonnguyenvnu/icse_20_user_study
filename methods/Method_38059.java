/** 
 * Detects database and returns  {@link DbServer}.
 */
public DbServer detectDatabase(final Connection connection){
  final String dbName;
  final int dbMajorVersion;
  final String version;
  try {
    log.info("Detecting database...");
    DatabaseMetaData databaseMetaData=connection.getMetaData();
    dbName=databaseMetaData.getDatabaseProductName();
    dbMajorVersion=databaseMetaData.getDatabaseMajorVersion();
    final int dbMinorVersion=databaseMetaData.getDatabaseMinorVersion();
    version=dbMajorVersion + "." + dbMinorVersion;
    log.info("Database: " + dbName + " v" + dbMajorVersion + "." + dbMinorVersion);
  }
 catch (  SQLException sex) {
    String msg=sex.getMessage();
    if (msg.contains("explicitly set for database: DB2")) {
      return new Db2DbServer();
    }
    throw new DbSqlException(sex);
  }
  if (dbName.equals("Apache Derby")) {
    return new DerbyDbServer(version);
  }
  if (dbName.startsWith("DB2/")) {
    return new Db2DbServer(version);
  }
  if (dbName.equals("HSQL Database Engine")) {
    return new HsqlDbServer(version);
  }
  if (dbName.equals("Informix Dynamic Server")) {
    return new InformixDbServer(version);
  }
  if (dbName.startsWith("Microsoft SQL Server")) {
    return new SqlServerDbServer(version);
  }
  if (dbName.equals("MySQL")) {
    return new MySqlDbServer(version);
  }
  if (dbName.equals("Oracle")) {
    return new OracleDbServer(version);
  }
  if (dbName.equals("PostgreSQL")) {
    return new PostgreSqlDbServer(version);
  }
  if (dbName.equals("Sybase SQL Server")) {
    return new SybaseDbServer(version);
  }
  if (dbName.equals("ASE") && (dbMajorVersion == 15)) {
    return new SybaseDbServer(version);
  }
  if (dbName.equals("SQLite")) {
    return new SQLiteDbServer(version);
  }
  return new GenericDbServer();
}
