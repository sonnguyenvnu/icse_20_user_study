/** 
 * Parse a line of text as comma-separated values, returning each value as one entry in an array of String objects. Remove quotes from entries that begin and end with them, and convert 'escaped' quotes to actual quotes.
 * @param line line of text to be parsed
 * @return an array of the individual values formerly separated by commas
 */
protected String[] splitLineCSV(String line,BufferedReader reader) throws IOException {
  if (csl == null) {
    csl=new CommaSeparatedLine();
  }
  return csl.handle(line,reader);
}
