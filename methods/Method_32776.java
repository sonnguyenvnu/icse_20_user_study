/** 
 * Is this years instance less than the specified number of years.
 * @param other  the other period, null means zero
 * @return true if this years instance is less than the specified one
 */
public boolean isLessThan(Years other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
