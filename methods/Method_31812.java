/** 
 * Does this time interval contain the current instant. <p> Non-zero duration intervals are inclusive of the start instant and exclusive of the end. A zero duration interval cannot contain anything.
 * @return true if this time interval contains the current instant
 */
public boolean containsNow(){
  return contains(DateTimeUtils.currentTimeMillis());
}
