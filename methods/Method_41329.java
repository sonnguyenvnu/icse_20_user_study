/** 
 * <p> This method should be overridden by any delegate subclasses that need special handling for BLOBs. The default implementation uses standard JDBC <code>java.sql.Blob</code> operations. </p>
 * @param rs the result set, already queued to the correct row
 * @param colName the column name for the BLOB
 * @return the deserialized Object from the ResultSet BLOB
 * @throws ClassNotFoundException if a class found during deserialization cannot be found
 * @throws IOException if deserialization causes an error
 */
@Override protected Object getObjectFromBlob(ResultSet rs,String colName) throws ClassNotFoundException, IOException, SQLException {
  InputStream binaryInput=null;
  byte[] bytes=rs.getBytes(colName);
  Object obj=null;
  if (bytes != null && bytes.length != 0) {
    binaryInput=new ByteArrayInputStream(bytes);
    ObjectInputStream in=new ObjectInputStream(binaryInput);
    try {
      obj=in.readObject();
    }
  finally {
      in.close();
    }
  }
  return obj;
}
