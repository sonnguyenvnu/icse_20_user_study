/** 
 * Parses input string and loads provided properties map.
 */
protected synchronized void parse(final String data){
  initialized=false;
  parser.parse(data);
}
