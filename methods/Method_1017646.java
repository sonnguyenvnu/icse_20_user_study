/** 
 * Truncates the large object to the given length in bytes. If the number of bytes is larger than the current large object length, the large object will be filled with zero bytes. This method does not modify the current file offset.
 * @param len given length in bytes
 * @throws SQLException if something goes wrong
 */
public void truncate(int len) throws SQLException {
  FastpathArg[] args=new FastpathArg[2];
  args[0]=new FastpathArg(fd);
  args[1]=new FastpathArg(len);
  fp.getInteger("lo_truncate",args);
}
