/** 
 * Adds SQL string quotes to the given string.
 * @param tableName The string to quote.
 * @return The quoted string.
 */
protected static String quote(String tableName){
  if (tableName == null) {
    return "null";
  }
 else {
    return String.format("'%s'",tableName);
  }
}
