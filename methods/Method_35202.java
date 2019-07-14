/** 
 * Returns whether or not this Router has a root  {@link Controller}
 */
public boolean hasRootController(){
  return getBackstackSize() > 0;
}
