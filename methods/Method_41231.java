/** 
 * Sets the designated parameter to the byte array of the given <code>ByteArrayOutputStream</code>.  Will set parameter value to null if the  <code>ByteArrayOutputStream</code> is null. Wraps <code> {@link PreparedStatement#setObject(int,java.lang.Object,int)}</code> rather than <code> {@link PreparedStatement#setBytes(int,byte[])}</code> as required by the  DB2 v7 database.
 */
@Override protected void setBytes(PreparedStatement ps,int index,ByteArrayOutputStream baos) throws SQLException {
  ps.setObject(index,((baos == null) ? null : baos.toByteArray()),java.sql.Types.BLOB);
}
