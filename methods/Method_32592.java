/** 
 * Adds the specified years to the number of years in the period.
 * @param years  the number of years
 * @throws IllegalArgumentException if field is not supported and the value is non-zero
 * @throws ArithmeticException if the addition exceeds the capacity of the period
 */
public void addYears(int years){
  super.addField(DurationFieldType.years(),years);
}
