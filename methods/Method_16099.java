@Override public void releaseConnection(Connection connection) throws SQLException {
  if (logger.isDebugEnabled()) {
    logger.debug("Releasing DataSource ({}) JDBC Connection [{}]",getDatasourceId(),connection);
  }
  try {
    DataSourceUtils.doReleaseConnection(connection,DataSourceHolder.currentDataSource().getNative());
  }
 catch (  SQLException e) {
    logger.error(e.getMessage(),e);
    try {
      connection.close();
    }
 catch (    Exception e2) {
      logger.error(e2.getMessage(),e2);
    }
  }
}
