/** 
 * Check that there are no years or months in the period.
 * @param destintionType  the destination type, not null
 * @throws UnsupportedOperationException if the period contains years or months
 */
private void checkYearsAndMonths(String destintionType){
  if (getMonths() != 0) {
    throw new UnsupportedOperationException("Cannot convert to " + destintionType + " as this period contains months and months vary in length");
  }
  if (getYears() != 0) {
    throw new UnsupportedOperationException("Cannot convert to " + destintionType + " as this period contains years and years vary in length");
  }
}
