/** 
 * Quotes a single command line argument if necessary.
 * @param arg command line argument
 * @return quoted argument
 */
static String quote(final String arg){
  final StringBuilder escaped=new StringBuilder();
  for (  final char c : arg.toCharArray()) {
    if (c == QUOTE || c == SLASH) {
      escaped.append(SLASH);
    }
    escaped.append(c);
  }
  if (arg.indexOf(BLANK) != -1 || arg.indexOf(QUOTE) != -1) {
    escaped.insert(0,QUOTE).append(QUOTE);
  }
  return escaped.toString();
}
