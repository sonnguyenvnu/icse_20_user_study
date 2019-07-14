/** 
 * Is this seconds instance greater than the specified number of seconds.
 * @param other  the other period, null means zero
 * @return true if this seconds instance is greater than the specified one
 */
public boolean isGreaterThan(Seconds other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
