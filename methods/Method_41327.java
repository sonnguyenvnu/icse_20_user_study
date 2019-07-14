/** 
 * <p> This method should be overridden by any delegate subclasses that need special handling for BLOBs. The default implementation uses standard JDBC <code>java.sql.Blob</code> operations. </p>
 * @param rs the result set, already queued to the correct row
 * @param colName the column name for the BLOB
 * @return the deserialized Object from the ResultSet BLOB
 * @throws ClassNotFoundException if a class found during deserialization cannot be found
 * @throws IOException if deserialization causes an error
 */
@Override protected Object getObjectFromBlob(ResultSet rs,String colName) throws ClassNotFoundException, IOException, SQLException {
  Object obj=null;
  byte binaryData[]=rs.getBytes(colName);
  InputStream binaryInput=new ByteArrayInputStream(binaryData);
  if (null != binaryInput && binaryInput.available() != 0) {
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
