@Override public String getString(String columnLabel) throws SQLException {
  return (String)current.get(headers.indexOf(columnLabel));
}
