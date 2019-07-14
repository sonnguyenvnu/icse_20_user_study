/** 
 * Is this days instance less than the specified number of days.
 * @param other  the other period, null means zero
 * @return true if this days instance is less than the specified one
 */
public boolean isLessThan(Days other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
