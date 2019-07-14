/** 
 * Set true to ensure that the order returned is identical. Slightly slower because the tie-breaker for identical values compares the keys.
 * @param stable
 */
public void sortValues(boolean stable){
  sortImpl(false,false,stable);
}
