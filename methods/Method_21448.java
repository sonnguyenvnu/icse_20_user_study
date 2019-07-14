@Override public long getLong(String columnLabel) throws SQLException {
  return (Long)current.get(headers.indexOf(columnLabel));
}
