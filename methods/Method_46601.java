@Override public short getShort(String columnLabel) throws SQLException {
  SQLException e=null;
  try {
    short value=delegate.getShort(columnLabel);
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,value,null);
    return value;
  }
 catch (  SQLException sqle) {
    e=sqle;
    eventListener.onAfterResultSetGet(resultSetInformation,columnLabel,null,e);
    throw e;
  }
}
