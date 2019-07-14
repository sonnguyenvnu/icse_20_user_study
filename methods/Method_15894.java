@Override public List<Object> getResult(CallableStatement cs,int columnIndex) throws SQLException {
  return parseArray(cs.getString(columnIndex));
}
