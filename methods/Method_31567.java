/** 
 * Opens a new connection from this dataSource.
 * @param dataSource     The dataSource to obtain the connection from.
 * @param connectRetries The maximum number of retries when attempting to connect to the database.
 * @return The new connection.
 * @throws FlywayException when the connection could not be opened.
 */
public static Connection openConnection(DataSource dataSource,int connectRetries) throws FlywayException {
  int retries=0;
  while (true) {
    try {
      return dataSource.getConnection();
    }
 catch (    SQLException e) {
      if (++retries > connectRetries) {
        throw new FlywaySqlException("Unable to obtain connection from database" + getDataSourceInfo(dataSource) + ": " + e.getMessage(),e);
      }
      Throwable rootCause=ExceptionUtils.getRootCause(e);
      String msg="Connection error: " + e.getMessage();
      if (rootCause != null && rootCause != e && rootCause.getMessage() != null) {
        msg+=" (Caused by " + rootCause.getMessage() + ")";
      }
      LOG.warn(msg + " Retrying in 1 sec...");
      try {
        Thread.sleep(1000);
      }
 catch (      InterruptedException e1) {
        throw new FlywaySqlException("Unable to obtain connection from database" + getDataSourceInfo(dataSource) + ": " + e.getMessage(),e);
      }
    }
  }
}
