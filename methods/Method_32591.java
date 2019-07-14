/** 
 * Sets the number of years of the period.
 * @param years  the number of years
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 */
public void setYears(int years){
  super.setField(DurationFieldType.years(),years);
}
