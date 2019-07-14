/** 
 * Is this hours instance less than the specified number of hours.
 * @param other  the other period, null means zero
 * @return true if this hours instance is less than the specified one
 */
public boolean isLessThan(Hours other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
