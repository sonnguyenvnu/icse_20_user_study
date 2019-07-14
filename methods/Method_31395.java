/** 
 * Checks whether the specified privilege or role is granted to the current user.
 * @return {@code true} if it is granted, {@code false} if not.
 * @throws SQLException if the check failed.
 */
boolean isPrivOrRoleGranted(String name) throws SQLException {
  return queryReturnsRows("SELECT 1 FROM SESSION_PRIVS WHERE PRIVILEGE = ? UNION ALL " + "SELECT 1 FROM SESSION_ROLES WHERE ROLE = ?",name,name);
}
