/** 
 * Sets the number of days of the period.
 * @param days  the number of days
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 */
public void setDays(int days){
  super.setField(DurationFieldType.days(),days);
}
