@Override public int getInt(String columnLabel) throws SQLException {
  return (Integer)current.get(headers.indexOf(columnLabel));
}
