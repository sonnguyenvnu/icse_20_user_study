/** 
 * Returns true if toPrinter can be called without throwing an UnsupportedOperationException.
 * @return true if a printer can be built
 */
public boolean canBuildPrinter(){
  return isPrinter(getFormatter());
}
