/** 
 * Sets the designated parameter to the byte array of the given <code>ByteArrayOutputStream</code>. Will set parameter value to null if the <code>ByteArrayOutputStream</code> is null. This just wraps <code> {@link PreparedStatement#setBytes(int,byte[])}</code> by default, but it can be overloaded by subclass delegates for databases that don't explicitly support storing bytes in this way.
 */
@Override protected void setBytes(PreparedStatement ps,int index,ByteArrayOutputStream baos) throws SQLException {
  ps.setObject(index,((baos == null) ? null : baos.toByteArray()),java.sql.Types.BLOB);
}
