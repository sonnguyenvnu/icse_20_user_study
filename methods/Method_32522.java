/** 
 * Is this minutes instance greater than the specified number of minutes.
 * @param other  the other period, null means zero
 * @return true if this minutes instance is greater than the specified one
 */
public boolean isGreaterThan(Minutes other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
