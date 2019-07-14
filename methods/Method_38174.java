@Override public void setObject(int parameterIndex,Object x) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setObject(parameterIndex,x);
}
