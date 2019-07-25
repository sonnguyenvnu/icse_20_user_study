/** 
 * This deletes a large object.
 * @param oid describing object to delete
 * @throws SQLException on error
 */
public void delete(long oid) throws SQLException {
  FastpathArg[] args=new FastpathArg[1];
  args[0]=Fastpath.createOIDArg(oid);
  fp.fastpath("lo_unlink",args);
}
