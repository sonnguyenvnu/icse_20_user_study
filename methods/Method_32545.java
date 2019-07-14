/** 
 * Is this months instance less than the specified number of months.
 * @param other  the other period, null means zero
 * @return true if this months instance is less than the specified one
 */
public boolean isLessThan(Months other){
  if (other == null) {
    return getValue() < 0;
  }
  return getValue() < other.getValue();
}
