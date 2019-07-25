/** 
 * Creates a database state writer given a connection URL.
 * @param url The connection URL to the database.
 * @return A database state writer that writes the given database.
 */
public static DbState writer(final String url){
  return new DbState(driverManager(url),commit);
}
