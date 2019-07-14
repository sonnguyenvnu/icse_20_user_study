/** 
 * @param connectionInfo
 * @return
 * @throws DatabaseServiceException
 */
private DatabaseInfo getMetadata(DatabaseConfiguration connectionInfo) throws DatabaseServiceException {
  try {
    Connection connection=MariaDBConnectionManager.getInstance().getConnection(connectionInfo,true);
    if (connection != null) {
      java.sql.DatabaseMetaData metadata=connection.getMetaData();
      int dbMajorVersion=metadata.getDatabaseMajorVersion();
      int dbMinorVersion=metadata.getDatabaseMinorVersion();
      String dbProductVersion=metadata.getDatabaseProductVersion();
      String dbProductName=metadata.getDatabaseProductName();
      DatabaseInfo dbInfo=new DatabaseInfo();
      dbInfo.setDatabaseMajorVersion(dbMajorVersion);
      dbInfo.setDatabaseMinorVersion(dbMinorVersion);
      dbInfo.setDatabaseProductVersion(dbProductVersion);
      dbInfo.setDatabaseProductName(dbProductName);
      return dbInfo;
    }
  }
 catch (  SQLException e) {
    logger.error("SQLException::",e);
    throw new DatabaseServiceException(true,e.getSQLState(),e.getErrorCode(),e.getMessage());
  }
  return null;
}
