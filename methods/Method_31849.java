/** 
 * Validates a period type, converting nulls to a default value and checking the type is suitable for this instance.
 * @param type  the type to check, may be null
 * @return the validated type to use, not null
 * @throws IllegalArgumentException if the period type is invalid
 */
protected PeriodType checkPeriodType(PeriodType type){
  return DateTimeUtils.getPeriodType(type);
}
