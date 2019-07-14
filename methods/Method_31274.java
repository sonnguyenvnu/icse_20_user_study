private static Parser createParser(JdbcConnectionFactory jdbcConnectionFactory,Configuration configuration){
  final DatabaseType databaseType=jdbcConnectionFactory.getDatabaseType();
switch (databaseType) {
case COCKROACHDB:
    return new CockroachDBParser(configuration);
case DB2:
  return new DB2Parser(configuration);
case DERBY:
return new DerbyParser(configuration);
case H2:
return new H2Parser(configuration);
case HSQLDB:
return new HSQLDBParser(configuration);
case INFORMIX:
return new InformixParser(configuration);
case MARIADB:
case MYSQL:
return new MySQLParser(configuration);
case ORACLE:
return new OracleParser(configuration);
case POSTGRESQL:
return new PostgreSQLParser(configuration);
case REDSHIFT:
return new RedshiftParser(configuration);
case SQLITE:
return new SQLiteParser(configuration);
case SAPHANA:
return new SAPHANAParser(configuration);
case SQLSERVER:
return new SQLServerParser(configuration);
case SYBASEASE_JCONNECT:
case SYBASEASE_JTDS:
return new SybaseASEParser(configuration);
default :
throw new FlywayException("Unsupported Database: " + databaseType.name());
}
}
