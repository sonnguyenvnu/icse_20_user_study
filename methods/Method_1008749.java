/** 
 * @see java.sql.Connection#rollback(java.sql.Savepoint)
 */
public void rollback(Savepoint savepoint) throws SQLException {
  checkOpen();
  if (getAutoCommit()) {
    throw new SQLException("database in auto-commit mode");
  }
  getDatabase().exec(String.format("ROLLBACK TO SAVEPOINT %s",savepoint.getSavepointName()),getAutoCommit());
}
