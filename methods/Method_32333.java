/** 
 * Checks if the parser is non null and a provider.
 * @param parser  the parser to check
 */
private void checkParser(DateTimeParser parser){
  if (parser == null) {
    throw new IllegalArgumentException("No parser supplied");
  }
}
