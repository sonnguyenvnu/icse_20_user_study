/** 
 * Returns a copy of this datetime with the specified period added. <p> If the addition is zero, then <code>this</code> is returned. <p> This method is typically used to add multiple copies of complex period instances. Adding one field is best achieved using methods like  {@link #withFieldAdded(DurationFieldType,int)}or  {@link #plusYears(int)}.
 * @param period  the period to add to this one, null means zero
 * @param scalar  the amount of times to add, such as -1 to subtract once
 * @return a copy of this datetime with the period added
 * @throws ArithmeticException if the result exceeds the internal capacity
 */
public LocalDateTime withPeriodAdded(ReadablePeriod period,int scalar){
  if (period == null || scalar == 0) {
    return this;
  }
  long instant=getChronology().add(period,getLocalMillis(),scalar);
  return withLocalMillis(instant);
}
