@Override public List<Object> getResult(ResultSet rs,String columnName) throws SQLException {
  return parseArray(rs.getString(columnName));
}
