/** 
 * Is this months instance greater than the specified number of months.
 * @param other  the other period, null means zero
 * @return true if this months instance is greater than the specified one
 */
public boolean isGreaterThan(Months other){
  if (other == null) {
    return getValue() > 0;
  }
  return getValue() > other.getValue();
}
