/** 
 * Is this weeks instance less than the specified number of weeks.
 * @param other  the other period, null means zero
 * @return true if this weeks instance is less than the specified one
 */
public boolean isLessThan(Weeks other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
