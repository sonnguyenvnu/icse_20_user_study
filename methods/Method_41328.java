/** 
 * <p> This method should be overridden by any delegate subclasses that need special handling for BLOBs for job details. The default implementation uses standard JDBC <code>java.sql.Blob</code> operations. </p>
 * @param rs the result set, already queued to the correct row
 * @param colName the column name for the BLOB
 * @return the deserialized Object from the ResultSet BLOB
 * @throws ClassNotFoundException if a class found during deserialization cannot be found
 * @throws IOException if deserialization causes an error
 */
@Override protected Object getJobDataFromBlob(ResultSet rs,String colName) throws ClassNotFoundException, IOException, SQLException {
  if (canUseProperties()) {
    byte data[]=rs.getBytes(colName);
    if (data == null) {
      return null;
    }
    InputStream binaryInput=new ByteArrayInputStream(data);
    return binaryInput;
  }
  return getObjectFromBlob(rs,colName);
}
