@Override public void setFetchSize(int rows) throws SQLException {
  wrappedStatement.setFetchDirection(rows);
}
