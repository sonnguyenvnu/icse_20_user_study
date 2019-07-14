@Override public short getShort(String columnLabel) throws SQLException {
  return ((Short)current.get(headers.indexOf(columnLabel)));
}
