@Override public Object getObject(String columnLabel) throws SQLException {
  return current.get(headers.indexOf(columnLabel));
}
