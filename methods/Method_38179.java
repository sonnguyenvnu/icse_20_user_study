@Override public void setObject(int parameterIndex,Object x,int targetSqlType,int scaleOrLength) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setObject(parameterIndex,x,targetSqlType,scaleOrLength);
}
