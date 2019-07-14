/** 
 * Detects the correct Jdbc driver for this Jdbc url.
 * @param url The Jdbc url.
 * @return The Jdbc driver.
 */
private String detectDriverForUrl(String url){
  if (url.startsWith(TEST_CONTAINERS_JDBC_URL_PREFIX)) {
    return TEST_CONTAINERS_JDBC_DRIVER;
  }
  if (url.startsWith(DB2_JDBC_URL_PREFIX)) {
    return "com.ibm.db2.jcc.DB2Driver";
  }
  if (url.startsWith(DERBY_CLIENT_JDBC_URL_PREFIX)) {
    return "org.apache.derby.jdbc.ClientDriver";
  }
  if (url.startsWith(DERBY_EMBEDDED_JDBC_URL_PREFIX)) {
    return "org.apache.derby.jdbc.EmbeddedDriver";
  }
  if (url.startsWith("jdbc:h2:")) {
    return "org.h2.Driver";
  }
  if (url.startsWith("jdbc:hsqldb:")) {
    return "org.hsqldb.jdbcDriver";
  }
  if (url.startsWith("jdbc:sqlite:")) {
    if (new FeatureDetector(classLoader).isAndroidAvailable()) {
      return SQLDROID_DRIVER;
    }
    return "org.sqlite.JDBC";
  }
  if (url.startsWith("jdbc:sqldroid:")) {
    return SQLDROID_DRIVER;
  }
  if (url.startsWith(MYSQL_JDBC_URL_PREFIX)) {
    return MYSQL_JDBC_DRIVER;
  }
  if (url.startsWith(MARIADB_JDBC_URL_PREFIX)) {
    return MARIADB_JDBC_DRIVER;
  }
  if (url.startsWith("jdbc:google:")) {
    return MYSQL_GOOGLE_JDBC_DRIVER;
  }
  if (url.startsWith(ORACLE_JDBC_URL_PREFIX)) {
    return "oracle.jdbc.OracleDriver";
  }
  if (url.startsWith(POSTGRESQL_JDBC_URL_PREFIX)) {
    return "org.postgresql.Driver";
  }
  if (url.startsWith(REDSHIFT_JDBC_URL_PREFIX)) {
    return "com.amazon.redshift.jdbc42.Driver";
  }
  if (url.startsWith("jdbc:jtds:")) {
    return "net.sourceforge.jtds.jdbc.Driver";
  }
  if (url.startsWith(SYBASE_JDBC_URL_PREFIX)) {
    return "com.sybase.jdbc4.jdbc.SybDriver";
  }
  if (url.startsWith(SQLSERVER_JDBC_URL_PREFIX)) {
    return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  }
  if (url.startsWith(SAPHANA_JDBC_URL_PREFIX)) {
    return "com.sap.db.jdbc.Driver";
  }
  if (url.startsWith("jdbc:informix-sqli:")) {
    return "com.informix.jdbc.IfxDriver";
  }
  return null;
}
