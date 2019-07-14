/** 
 * Checks whether parsing is supported.
 * @throws UnsupportedOperationException if parsing is not supported
 */
private InternalParser requireParser(){
  InternalParser parser=iParser;
  if (parser == null) {
    throw new UnsupportedOperationException("Parsing not supported");
  }
  return parser;
}
