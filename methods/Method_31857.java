/** 
 * Merges the fields from another period.
 * @param values  the array of values to update
 * @param period  the period to add from, not null
 * @return the updated values
 * @throws IllegalArgumentException if an unsupported field's value is non-zero
 */
protected int[] mergePeriodInto(int[] values,ReadablePeriod period){
  for (int i=0, isize=period.size(); i < isize; i++) {
    DurationFieldType type=period.getFieldType(i);
    int value=period.getValue(i);
    checkAndUpdate(type,values,value);
  }
  return values;
}
