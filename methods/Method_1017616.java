/** 
 * <p>Send a function call to the PostgreSQL backend by name.</p> <p>Note: the mapping for the procedure name to function id needs to exist, usually to an earlier call to addfunction().</p> <p>This is the preferred method to call, as function id's can/may change between versions of the backend.</p> <p>For an example of how this works, refer to org.postgresql.largeobject.LargeObject</p>
 * @param name Function name
 * @param args FastpathArguments to pass to fastpath
 * @return null if no data, byte[] otherwise
 * @throws SQLException if name is unknown or if a database-access error occurs.
 * @see org.postgresql.largeobject.LargeObject
 */
public byte[] fastpath(String name,FastpathArg[] args) throws SQLException {
  connection.getLogger().log(Level.FINEST,"Fastpath: calling {0}",name);
  return fastpath(getID(name),args);
}
