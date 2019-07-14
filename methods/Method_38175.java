@Override public void setRef(int parameterIndex,Ref x) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setRef(parameterIndex,x);
}
