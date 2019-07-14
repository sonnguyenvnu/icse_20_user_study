/** 
 * Checks whether this schema is default for the current user.
 * @return {@code true} if it is default, {@code false} if not.
 */
boolean isDefaultSchemaForUser() throws SQLException {
  return name.equals(database.doGetCurrentUser());
}
