/** 
 * Loads SQLite native JDBC library.
 * @return True if SQLite native library is successfully loaded; falseotherwise.
 */
public static synchronized boolean initialize() throws Exception {
  if (!extracted) {
    cleanup();
  }
  loadSQLiteNativeLibrary();
  return extracted;
}
