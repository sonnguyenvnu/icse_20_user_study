@Override public void setURL(int parameterIndex,URL x) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setURL(parameterIndex,x);
}
