/** 
 * Is this years instance greater than the specified number of years.
 * @param other  the other period, null means zero
 * @return true if this years instance is greater than the specified one
 */
public boolean isGreaterThan(Years other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
