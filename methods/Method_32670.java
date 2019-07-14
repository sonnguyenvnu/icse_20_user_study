/** 
 * Is this seconds instance less than the specified number of seconds.
 * @param other  the other period, null means zero
 * @return true if this seconds instance is less than the specified one
 */
public boolean isLessThan(Seconds other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
