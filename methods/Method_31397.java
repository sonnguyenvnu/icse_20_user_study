/** 
 * Returns the specified data dictionary view name prefixed with DBA_ or ALL_ depending on its accessibility.
 * @param baseName the data dictionary view base name, unquoted case-sensitive, e.g. OBJECTS, TABLES.
 * @return the full name of the view with the proper prefix.
 * @throws SQLException if the check failed.
 */
String dbaOrAll(String baseName) throws SQLException {
  return isPrivOrRoleGranted("SELECT ANY DICTIONARY") || isDataDictViewAccessible("DBA_" + baseName) ? "DBA_" + baseName : "ALL_" + baseName;
}
