/** 
 * Retrieves a second choice backup driver for a JDBC URL, in case the primary driver is not available.
 * @param url The JDBC url.
 * @return The JDBC driver. {@code null} if none.
 */
private String detectBackupDriverForUrl(String url){
  if (url.startsWith(MYSQL_JDBC_URL_PREFIX) && ClassUtils.isPresent(MYSQL_LEGACY_JDBC_DRIVER,classLoader)) {
    return MYSQL_LEGACY_JDBC_DRIVER;
  }
  if (url.startsWith(MYSQL_JDBC_URL_PREFIX) && ClassUtils.isPresent(MARIADB_JDBC_DRIVER,classLoader)) {
    LOG.warn("You are attempting to connect to a MySQL database using the MariaDB driver." + " This is known to cause issues." + " An upgrade to Oracle's MySQL JDBC driver is highly recommended.");
    return MARIADB_JDBC_DRIVER;
  }
  if (url.startsWith(REDSHIFT_JDBC_URL_PREFIX)) {
    if (ClassUtils.isPresent(REDSHIFT_JDBC41_DRIVER,classLoader)) {
      return REDSHIFT_JDBC41_DRIVER;
    }
    return REDSHIFT_JDBC4_DRIVER;
  }
  return null;
}
