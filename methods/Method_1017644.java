/** 
 * <p>Sets the current position within the object.</p> <p>This is similar to the fseek() call in the standard C library. It allows you to have random access to the large object.</p>
 * @param pos position within object from beginning
 * @throws SQLException if a database-access error occurs.
 */
public void seek(int pos) throws SQLException {
  seek(pos,SEEK_SET);
}
