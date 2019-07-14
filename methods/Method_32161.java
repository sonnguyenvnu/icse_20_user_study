/** 
 * Is this days instance greater than the specified number of days.
 * @param other  the other period, null means zero
 * @return true if this days instance is greater than the specified one
 */
public boolean isGreaterThan(Days other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
