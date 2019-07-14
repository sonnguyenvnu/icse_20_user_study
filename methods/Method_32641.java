/** 
 * Returns a new period minus the specified number of days taken away. <p> This period instance is immutable and unaffected by this method call.
 * @param days  the amount of days to take away, may be negative
 * @return the new period minus the increased days
 * @throws UnsupportedOperationException if the field is not supported
 */
public Period minusDays(int days){
  return plusDays(-days);
}
