/** 
 * Is this minutes instance less than the specified number of minutes.
 * @param other  the other period, null means zero
 * @return true if this minutes instance is less than the specified one
 */
public boolean isLessThan(Minutes other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
