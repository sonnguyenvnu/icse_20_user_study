/** 
 * Parses the code and determines the mode of the sketch.
 * @param code code without comments
 * @return determined mode
 */
static public Mode parseMode(CharSequence code){
  if (findInCurrentScope(FUNCTION_DECL,code) != null) {
    return Mode.ACTIVE;
  }
  if (findInCurrentScope(PUBLIC_CLASS,code) != null) {
    return Mode.JAVA;
  }
  return Mode.STATIC;
}
