@Override public Map<String,Object> getResult(ResultSet rs,int columnIndex) throws SQLException {
  return parseObject(rs.getString(columnIndex));
}
