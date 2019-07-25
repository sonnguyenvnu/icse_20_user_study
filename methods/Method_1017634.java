@Override public void rollback(Savepoint savepoint) throws SQLException {
  checkClosed();
  PSQLSavepoint pgSavepoint=(PSQLSavepoint)savepoint;
  execSQLUpdate("ROLLBACK TO SAVEPOINT " + pgSavepoint.getPGName());
}
