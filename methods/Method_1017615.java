/** 
 * @param name Function name
 * @param resulttype True if the result is a numeric (Integer or Long)
 * @param args FastpathArguments to pass to fastpath
 * @return null if no data, Integer if an integer result, Long if a long result, or byte[]otherwise
 * @throws SQLException if something goes wrong
 * @see #fastpath(int,FastpathArg[])
 * @see #fastpath(String,FastpathArg[])
 * @deprecated Use {@link #getData(String,FastpathArg[])} if you expect a binary result, or oneof  {@link #getInteger(String,FastpathArg[])} or{@link #getLong(String,FastpathArg[])} if you expect a numeric one
 */
@Deprecated public Object fastpath(String name,boolean resulttype,FastpathArg[] args) throws SQLException {
  connection.getLogger().log(Level.FINEST,"Fastpath: calling {0}",name);
  return fastpath(getID(name),resulttype,args);
}
