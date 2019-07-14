/** 
 * Is this hours instance greater than the specified number of hours.
 * @param other  the other period, null means zero
 * @return true if this hours instance is greater than the specified one
 */
public boolean isGreaterThan(Hours other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
