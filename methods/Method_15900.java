@Override public Map<String,Object> getResult(CallableStatement cs,int columnIndex) throws SQLException {
  return parseObject(cs.getString(columnIndex));
}
