/** 
 * Safely closes this statement. This method never fails.
 * @param statement The statement to close.
 */
public static void closeStatement(Statement statement){
  if (statement == null) {
    return;
  }
  try {
    statement.close();
  }
 catch (  SQLException e) {
    LOG.error("Error while closing JDBC statement",e);
  }
}
