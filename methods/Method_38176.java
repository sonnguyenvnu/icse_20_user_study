@Override public void setNull(int parameterIndex,int sqlType,String typeName) throws SQLException {
  saveQueryParamValue(parameterIndex,null);
  wrappedStatement.setNull(parameterIndex,sqlType,typeName);
}
