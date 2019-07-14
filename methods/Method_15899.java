@Override public Map<String,Object> getResult(ResultSet rs,String columnName) throws SQLException {
  return parseObject(rs.getString(columnName));
}
