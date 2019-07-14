/** 
 * Resets the delimiter to its default value before parsing a new statement.
 */
protected void resetDelimiter(ParserContext context){
  context.setDelimiter(getDefaultDelimiter());
}
