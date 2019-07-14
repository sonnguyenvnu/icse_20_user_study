/** 
 * testConnection
 * @param databaseConfiguration
 * @return
 */
public boolean testConnection(DatabaseConfiguration databaseConfiguration) throws DatabaseServiceException {
  try {
    boolean connResult=false;
    Connection conn=getConnection(databaseConfiguration,true);
    if (conn != null) {
      connResult=true;
      conn.close();
    }
    return connResult;
  }
 catch (  SQLException e) {
    logger.error("Test connection Failed!",e);
    throw new DatabaseServiceException(true,e.getSQLState(),e.getErrorCode(),e.getMessage());
  }
}
