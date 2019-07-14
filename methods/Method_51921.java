/** 
 * Returns true if this is a lower type bound, e.g. in  {@code <? super Node>}.
 */
public boolean isLowerBound(){
  return !isUpperBound();
}
