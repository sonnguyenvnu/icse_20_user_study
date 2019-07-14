@Override public void setTimestamp(String parameterName,Timestamp x,Calendar cal) throws SQLException {
  SQLException e=null;
  try {
    delegate.setTimestamp(parameterName,x,cal);
  }
 catch (  SQLException sqle) {
    e=sqle;
    throw e;
  }
 finally {
    eventListener.onAfterCallableStatementSet(statementInformation,parameterName,x,e);
  }
}
