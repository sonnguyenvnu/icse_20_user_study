/** 
 * Builds a single command line string from the given argument list. Arguments are quoted when necessary.
 * @param args command line arguments
 * @return combined command line
 */
static String quote(final List<String> args){
  final StringBuilder result=new StringBuilder();
  boolean separate=false;
  for (  final String arg : args) {
    if (separate) {
      result.append(BLANK);
    }
    result.append(quote(arg));
    separate=true;
  }
  return result.toString();
}
