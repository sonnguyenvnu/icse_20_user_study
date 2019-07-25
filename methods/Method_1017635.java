@Override public boolean execute(String sql) throws SQLException {
  throw new PSQLException(GT.tr("Can''t use query methods that take a query string on a PreparedStatement."),PSQLState.WRONG_OBJECT_TYPE);
}
