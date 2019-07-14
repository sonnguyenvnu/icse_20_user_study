@Override public void setRowId(String parameterName,RowId x) throws SQLException {
  SQLException e=null;
  try {
    delegate.setRowId(parameterName,x);
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    eventListener.onAfterCallableStatementSet(statementInformation,parameterName,x,e);
  }
}
