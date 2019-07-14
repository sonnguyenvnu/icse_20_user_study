private static Database createDatabase(DatabaseType databaseType,Configuration configuration,JdbcConnectionFactory jdbcConnectionFactory){
switch (databaseType) {
case COCKROACHDB:
    return new CockroachDBDatabase(configuration,jdbcConnectionFactory);
case DB2:
  return new DB2Database(configuration,jdbcConnectionFactory);
case DERBY:
return new DerbyDatabase(configuration,jdbcConnectionFactory);
case H2:
return new H2Database(configuration,jdbcConnectionFactory);
case HSQLDB:
return new HSQLDBDatabase(configuration,jdbcConnectionFactory);
case INFORMIX:
return new InformixDatabase(configuration,jdbcConnectionFactory);
case MARIADB:
case MYSQL:
return new MySQLDatabase(configuration,jdbcConnectionFactory);
case ORACLE:
return new OracleDatabase(configuration,jdbcConnectionFactory);
case POSTGRESQL:
return new PostgreSQLDatabase(configuration,jdbcConnectionFactory);
case REDSHIFT:
return new RedshiftDatabase(configuration,jdbcConnectionFactory);
case SQLITE:
return new SQLiteDatabase(configuration,jdbcConnectionFactory);
case SAPHANA:
return new SAPHANADatabase(configuration,jdbcConnectionFactory);
case SQLSERVER:
return new SQLServerDatabase(configuration,jdbcConnectionFactory);
case SYBASEASE_JCONNECT:
case SYBASEASE_JTDS:
return new SybaseASEDatabase(configuration,jdbcConnectionFactory);
default :
throw new FlywayException("Unsupported Database: " + databaseType.name());
}
}
