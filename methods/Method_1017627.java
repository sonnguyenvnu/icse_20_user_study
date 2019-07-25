/** 
 * time stamp add.
 * @param parsedArgs arguments
 * @return sql call
 * @throws SQLException if something wrong happens
 */
public static String sqltimestampadd(List<?> parsedArgs) throws SQLException {
  if (parsedArgs.size() != 3) {
    throw new PSQLException(GT.tr("{0} function takes three and only three arguments.","timestampadd"),PSQLState.SYNTAX_ERROR);
  }
  String interval=EscapedFunctions.constantToInterval(parsedArgs.get(0).toString(),parsedArgs.get(1).toString());
  StringBuilder buf=new StringBuilder();
  buf.append("(").append(interval).append("+");
  buf.append(parsedArgs.get(2)).append(")");
  return buf.toString();
}
