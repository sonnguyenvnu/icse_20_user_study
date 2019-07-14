/** 
 * Builds the query and returns parsed data. Returned value can be cached or stored as a constant value to prevent further parsing of the same code.
 */
public ParsedSql parse(){
  return new ParsedSql(this);
}
