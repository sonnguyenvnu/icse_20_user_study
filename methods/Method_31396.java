/** 
 * Checks whether the specified data dictionary view in the specified system schema is accessible (directly or through a role) or not.
 * @param owner the schema name, unquoted case-sensitive.
 * @param name  the data dictionary view name to check, unquoted case-sensitive.
 * @return {@code true} if it is accessible, {@code false} if not.
 * @throws SQLException if the check failed.
 */
private boolean isDataDictViewAccessible(String owner,String name) throws SQLException {
  return queryReturnsRows("SELECT * FROM ALL_TAB_PRIVS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?" + " AND PRIVILEGE = 'SELECT'",owner,name);
}
