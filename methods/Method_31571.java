/** 
 * Retrieves the name of the database product.
 * @param databaseMetaData The connection metadata to use to query the database.
 * @return The name of the database product. Ex.: Oracle, MySQL, ...
 */
public static String getDatabaseProductName(DatabaseMetaData databaseMetaData){
  try {
    String databaseProductName=databaseMetaData.getDatabaseProductName();
    if (databaseProductName == null) {
      throw new FlywayException("Unable to determine database. Product name is null.");
    }
    int databaseMajorVersion=databaseMetaData.getDatabaseMajorVersion();
    int databaseMinorVersion=databaseMetaData.getDatabaseMinorVersion();
    return databaseProductName + " " + databaseMajorVersion + "." + databaseMinorVersion;
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Error while determining database product name",e);
  }
}
