/** 
 * Reads some data from the object, and return as a byte[] array.
 * @param len number of bytes to read
 * @return byte[] array containing data read
 * @throws SQLException if a database-access error occurs.
 */
public byte[] read(int len) throws SQLException {
  FastpathArg[] args=new FastpathArg[2];
  args[0]=new FastpathArg(fd);
  args[1]=new FastpathArg(len);
  return fp.getData("loread",args);
}
