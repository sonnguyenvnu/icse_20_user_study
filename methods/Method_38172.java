@Override public void setByte(int parameterIndex,byte x) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setByte(parameterIndex,x);
}
