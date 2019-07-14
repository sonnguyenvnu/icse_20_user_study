@Override public List<Object> getResult(ResultSet rs,int columnIndex) throws SQLException {
  return parseArray(rs.getString(columnIndex));
}
