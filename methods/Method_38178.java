@Override public void setRowId(int parameterIndex,RowId x) throws SQLException {
  saveQueryParamValue(parameterIndex,x);
  wrappedStatement.setRowId(parameterIndex,x);
}
