/** 
 * Checks whether the period is non-null.
 * @throws IllegalArgumentException if the period is null
 */
private void checkPeriod(ReadablePeriod period){
  if (period == null) {
    throw new IllegalArgumentException("Period must not be null");
  }
}
