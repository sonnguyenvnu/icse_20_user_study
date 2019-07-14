/** 
 * Is this weeks instance greater than the specified number of weeks.
 * @param other  the other period, null means zero
 * @return true if this weeks instance is greater than the specified one
 */
public boolean isGreaterThan(Weeks other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
