/** 
 * power to pow translation
 * @param buf The buffer to append into
 * @param parsedArgs arguments
 * @throws SQLException if something wrong happens
 */
public static void sqlpower(StringBuilder buf,List<? extends CharSequence> parsedArgs) throws SQLException {
  twoArgumentsFunctionCall(buf,"pow(","power",parsedArgs);
}
