/** 
 * Returns a new period with the specified number of years taken away. <p> This period instance is immutable and unaffected by this method call.
 * @param years  the amount of years to take away, may be negative
 * @return the new period with the increased years
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period minusYears(int years){
  return plusYears(-years);
}
