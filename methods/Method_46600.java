@Override public short getShort(int columnIndex) throws SQLException {
  SQLException e=null;
  try {
    short value=delegate.getShort(columnIndex);
    eventListener.onAfterResultSetGet(resultSetInformation,columnIndex,value,null);
    return value;
  }
 catch (  SQLException sqle) {
    e=sqle;
    eventListener.onAfterResultSetGet(resultSetInformation,columnIndex,null,e);
    throw e;
  }
}
