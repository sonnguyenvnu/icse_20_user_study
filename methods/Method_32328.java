/** 
 * Returns true if toFormatter can be called without throwing an UnsupportedOperationException.
 * @return true if a formatter can be built
 */
public boolean canBuildFormatter(){
  return isFormatter(getFormatter());
}
