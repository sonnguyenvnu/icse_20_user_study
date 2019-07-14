/** 
 * Returns true if toParser can be called without throwing an UnsupportedOperationException.
 * @return true if a parser can be built
 */
public boolean canBuildParser(){
  return isParser(getFormatter());
}
