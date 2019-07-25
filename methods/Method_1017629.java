/** 
 * truncate to trunc translation
 * @param buf The buffer to append into
 * @param parsedArgs arguments
 * @throws SQLException if something wrong happens
 */
public static void sqltruncate(StringBuilder buf,List<? extends CharSequence> parsedArgs) throws SQLException {
  twoArgumentsFunctionCall(buf,"trunc(","truncate",parsedArgs);
}
