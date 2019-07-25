/** 
 * Loads the SQLite interface backend.
 * @return True if the SQLite JDBC driver is successfully loaded; false otherwise.
 */
public static boolean load() throws Exception {
  if (isLoaded)   return loadSucceeded == true;
  loadSucceeded=SQLiteJDBCLoader.initialize();
  isLoaded=true;
  return loadSucceeded;
}
