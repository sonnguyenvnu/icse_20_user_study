/** 
 * Send a function call to the PostgreSQL backend.
 * @param fnId Function id
 * @param args FastpathArguments to pass to fastpath
 * @return null if no data, byte[] otherwise
 * @throws SQLException if a database-access error occurs.
 */
public byte[] fastpath(int fnId,FastpathArg[] args) throws SQLException {
  ParameterList params=executor.createFastpathParameters(args.length);
  for (int i=0; i < args.length; ++i) {
    args[i].populateParameter(params,i + 1);
  }
  return executor.fastpathCall(fnId,params,connection.getAutoCommit());
}
