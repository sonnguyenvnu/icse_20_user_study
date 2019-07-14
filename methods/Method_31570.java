/** 
 * Safely closes this resultSet. This method never fails.
 * @param resultSet The resultSet to close.
 */
public static void closeResultSet(ResultSet resultSet){
  if (resultSet == null) {
    return;
  }
  try {
    resultSet.close();
  }
 catch (  SQLException e) {
    LOG.error("Error while closing JDBC resultSet",e);
  }
}
