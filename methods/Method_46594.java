@Override public void setInt(String parameterName,int x) throws SQLException {
  SQLException e=null;
  try {
    delegate.setInt(parameterName,x);
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    eventListener.onAfterCallableStatementSet(statementInformation,parameterName,x,e);
  }
}
