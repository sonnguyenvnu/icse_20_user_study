/** 
 * Sets the file name prefix for undo SQL migrations. (default: U) <p>Undo SQL migrations are responsible for undoing the effects of the versioned migration with the same version.</p> <p>They have the following file name structure: prefixVERSIONseparatorDESCRIPTIONsuffix , which using the defaults translates to U1.1__My_description.sql</p> <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param undoSqlMigrationPrefix The file name prefix for undo SQL migrations. (default: U)
 */
public void setUndoSqlMigrationPrefix(String undoSqlMigrationPrefix){
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("undoSqlMigrationPrefix");
}
