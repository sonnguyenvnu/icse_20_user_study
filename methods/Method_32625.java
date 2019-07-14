/** 
 * Creates a new Period instance with the fields from the specified period copied on top of those from this period. <p> This period instance is immutable and unaffected by this method call.
 * @param period  the period to copy from, null ignored
 * @return the new period instance
 * @throws IllegalArgumentException if a field type is unsupported
 */
public Period withFields(ReadablePeriod period){
  if (period == null) {
    return this;
  }
  int[] newValues=getValues();
  newValues=super.mergePeriodInto(newValues,period);
  return new Period(newValues,getPeriodType());
}
