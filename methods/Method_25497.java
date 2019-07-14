/** 
 * Returns the name of the first check, or  {@code ""}. 
 */
@Override public String toString(){
  return getFirst(getAllChecks().keySet(),"");
}
