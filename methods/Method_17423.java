/** 
 * Returns whether the request resulted in a write to the database. 
 */
private boolean isRead(String line){
  return line.charAt(line.length() - 1) == '-';
}
