/** 
 * @return the current position within the object
 * @throws SQLException if a database-access error occurs.
 */
public int tell() throws SQLException {
  FastpathArg[] args=new FastpathArg[1];
  args[0]=new FastpathArg(fd);
  return fp.getInteger("lo_tell",args);
}
