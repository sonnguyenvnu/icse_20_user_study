/** 
 * Creates a database state reader given a connection URL.
 * @param url The connection URL to the database.
 * @return A database state reader that reads the given database.
 */
public static DbState reader(final String url){
  return new DbState(driverManager(url),rollback);
}
