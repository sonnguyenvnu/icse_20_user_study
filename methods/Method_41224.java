/** 
 * Sets the designated parameter to the byte array of the given <code>ByteArrayOutputStream</code>. Will set parameter value to null if the <code>ByteArrayOutputStream</code> is null. This just wraps <code> {@link PreparedStatement#setBytes(int,byte[])}</code> by default, but it can be overloaded by subclass delegates for databases that don't explicitly support storing bytes in this way.
 */
@Override protected void setBytes(PreparedStatement ps,int index,ByteArrayOutputStream baos) throws SQLException {
  byte[] byteArray;
  if (baos == null) {
    byteArray=new byte[0];
  }
 else {
    byteArray=baos.toByteArray();
  }
  Connection conn=ps.getConnection();
  if (conn instanceof C3P0ProxyConnection) {
    try {
      C3P0ProxyConnection c3p0Conn=(C3P0ProxyConnection)conn;
      Method m=Connection.class.getMethod("createBlob",new Class[]{});
      Object[] args=new Object[]{};
      Blob blob=(Blob)c3p0Conn.rawConnectionOperation(m,C3P0ProxyConnection.RAW_CONNECTION,args);
      blob.setBytes(1,byteArray);
      ps.setBlob(index,blob);
    }
 catch (    Exception ex) {
      ex.printStackTrace();
    }
  }
 else {
    Blob blob=ps.getConnection().createBlob();
    blob.setBytes(1,byteArray);
    ps.setBlob(index,blob);
  }
}
